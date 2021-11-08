package ggc.core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.lang.model.element.QualifiedNameable;

import java.util.HashMap;
import java.io.IOException;

import ggc.app.exception.UnknownTransactionKeyException;
import ggc.core.exception.BadEntryException;
import ggc.core.exception.DuplicatePartnerIdException;
import ggc.core.exception.InvalidDateException;
import ggc.core.exception.InvalidPartnerIdException;
import ggc.core.exception.InvalidProductIdException;
import ggc.core.exception.InvalidTransactionKeyException;
import ggc.core.exception.ProductAmountException;
import ggc.core.partners.Partner;
import ggc.core.partners.PartnerComparator;
import ggc.core.products.AggregateProduct;
import ggc.core.products.Component;
import ggc.core.products.Product;
import ggc.core.products.ProductComparator;
import ggc.core.products.SimpleProduct;
import ggc.core.transactions.Acquisition;
import ggc.core.transactions.SaleByCredit;
import ggc.core.transactions.Transaction;


public class Warehouse implements Serializable {
  private static final long serialVersionUID = 202109192006L;
  private Date _date;
  private Map<String, Partner> _partners;
  private Map<String, Product> _products;
  private List<Notification> _notifications;
  private List<Transaction> _transactions;
  private int _nextTransctionId;
  
  public Warehouse(){
    _date = new Date(0);
    _partners = new HashMap<String, Partner>();
    _products = new HashMap<String, Product>();
    _notifications = new ArrayList<>();
    _transactions = new ArrayList<>();
  }
  

//DATE

  public int currentDate(){
    return _date.currentDate();
  }
  public void advanceDate(int days) throws InvalidDateException{
    _date.advanceDate(days);
  }


  //PRODUCT

  public Product registerSimpleProduct(String id){
    SimpleProduct product = new SimpleProduct(id);
    _products.put(id, product);
    return product;
  }

  public Product registerAggregateProduct(String id, double aggravation, List<Component> comps){
    AggregateProduct product = new AggregateProduct(id, aggravation, comps);
    _products.put(id, product);
    return product;
  }

  public Product registerAggregateProduct(String id, double aggravation,List<String> ids, List<Integer> qnts) throws InvalidProductIdException{
    ArrayList<Component> comps = new ArrayList<>();
    for(int i=0;i<ids.size();i++){
        comps.add(new Component(getProduct(ids.get(i)), (int)qnts.get(i)));
    }
    AggregateProduct product = new AggregateProduct(id, aggravation, comps);
    _products.put(id, product);
    return product;
  }
  
  public Product getProduct(String id) throws InvalidProductIdException{
    if(!_products.containsKey(id)){
      throw new InvalidProductIdException(id);
    }
    return _products.get(id);
  }
  public Map<String, Product> getProductMap(){
    return _products;
  }
  public List<Product> getProductList(){
    return new ArrayList<Product>(_products.values());
  }
  public List<Product> getProductSortedList(){
    List<Product> productList = new ArrayList<>(_products.values());
    productList.sort(new ProductComparator());
    return productList;
  }


//BATCH

  public void registerBatch(double price, int quantity,Partner partner,Product product){
    partner.registerBatch(price, quantity, partner, product);
    product.addBatch(new Batch(price, quantity, partner, product));
    product.updateMaxPrice();
    addNotificationToSystem("NEW", product, price);
  }
  public List<Batch> getBatchList(){
    List<Batch> batches= new ArrayList<>();
    for(Product product : new ArrayList<>(_products.values()))
      for(Batch batch : product.getBatches())
        batches.add(batch);
    return batches;
  }
  public List<Batch> sortBatches(List<Batch> batches){
    batches.sort(new BatchComparator());
    return batches;
  }
  public List<Batch> getBatchSortedList(){
    return sortBatches(getBatchList());
  }
  public List<Batch> getBatchSortedList(Product product){
    return sortBatches(product.getBatches());
  }
  
  public List<Batch> getBatchSortedList(Partner partner){
    return sortBatches(partner.getBatches());
  }


//PARTNER

  public void registerPartner(String id, String name, String adress) throws DuplicatePartnerIdException{
    if(_partners.containsKey(id.toLowerCase())){
      throw new DuplicatePartnerIdException(id);
    }
    Partner partner = new Partner(id, name, adress);
    _partners.put(id.toLowerCase(),partner);
    //addNotificationsToPartner(partner);
  }
  public Partner getPartner(String id) throws InvalidPartnerIdException{
    if(_partners.containsKey(id.toLowerCase())){
      return _partners.get(id.toLowerCase());
    } else{
      throw new InvalidPartnerIdException(id);
    }
  }
  public Map<String, Partner> getPartnerMap(){
    return _partners;
  }
  public List<Partner> getPartnerList(){
    return new ArrayList<>(_partners.values());
  }
  public List<Partner> getPartnerSortedList(){
    List<Partner> partnerList = getPartnerList();
    partnerList.sort(new PartnerComparator());
    return partnerList;
  }


//TRANSACTION

  public int getTransactionId(){
    return _nextTransctionId;
  }
  public void advanceTransactionId(){
    _nextTransctionId++;
  }

  public void registerAcquisition(Partner partner, Product product, int quantity, double price){
    registerBatch(price, quantity, partner, product);
    product.updateMaxPrice();
    Acquisition acq = new Acquisition(partner,product, quantity, _nextTransctionId);
    partner.registerAcquisition(acq);
    _transactions.add(acq); 
    advanceTransactionId();
  }

  public void registerSaleByCredit(String partnerId, String productId, int quantity, int deadline) throws ProductAmountException, InvalidProductIdException, InvalidPartnerIdException{
    if(getProduct(productId).getQuantity()<quantity){
      throw new ProductAmountException(productId,quantity);
    }
    getProduct(productId).updateMaxPrice();
    _transactions.add(new SaleByCredit(getPartner(partnerId),getProduct(productId), quantity, deadline,_nextTransctionId));
    advanceTransactionId();
    /*
    double _baseValue=0;
    Batch removingBatch = getProduct(productId).searchCheapestBatch();
    while(quantity > 0){
      if(removingBatch.getQuantity()<quantity){
        quantity -= removingBatch.getQuantity();
        _baseValue += removingBatch.getQuantity()*removingBatch.getPrice();
        removeBatch(removingBatch);
      } else{
        _baseValue += quantity*removingBatch.getPrice();
        quantity-= removingBatch.getQuantity();
        removingBatch.removeQuantity(quantity);
      }
    }*/
  }


  public Transaction getTransaction(int transactionId) throws InvalidTransactionKeyException{
    //System.out.println(_transactions.size());
    //System.out.println(transactionId);
    if(_transactions.size()<=transactionId){
      throw new InvalidTransactionKeyException(transactionId);
    }
    return _transactions.get(transactionId);
  }


  public List<Transaction> getTransactionList(){
    return _transactions;
  }

//NOTIFICATION
  
  public void addNotificationToSystem(String type, Product product, double price){
    for(Notification notification: _notifications){
      if(notification.equals(new Notification("NEW", product, price))){
        

      }
    }


  }
  /*
  public void addNotificationToSystem(Product product, double price){
    if(!_notfications.keySet().contains(product.getId())){
      Notification notification = new Notification("NEW", product, price);
      _notifications.put(product.getId(), notification);
    }
    else if(_notifications.keySet().contains(product.getId())){
      if(price<product.getMinPrice()){
        Notification notification = new Notification("BARGAIN", product, price);
        _notifications.put(product.getId(), notification);
      }
    }
  }

  public void addNotificationToPartners(Notification notification, Product product){
    for(Partner partner: _partners.values()){
      partner.addNotification(notification.getType(), product);
    }
  }
  
  public void addNotificationsToPartner(Partner partner) {
    for(Notification notification: _notifications.values()){
      partner.addNotification(notification.getType(), notification.getProduct());
    }
  }
  */

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   * @throws InvalidProductIdException
   * @throws InvalidPartnerIdException
   * @throws DuplicatePartnerIdException
   */
  void importFile(String txtfile) throws IOException, BadEntryException, DuplicatePartnerIdException, InvalidPartnerIdException, InvalidProductIdException {
    Parser parser = new Parser(this);
    parser.parseFile(txtfile);
  }


}
