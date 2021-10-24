package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import ggc.core.exception.BadEntryException;

public class Warehouse implements Serializable {
  private static final long serialVersionUID = 202109192006L;
  private Date _date;
  private HashMap<String, Partner> _partners;
  private HashMap<String, Product> _products;
  
  public Warehouse(){
    _date = new Date();
    _partners = new HashMap<String, Partner>();
  }
  

  
  public int currentDate(){
    return _date.currentDate();
  }
  public void advanceDate(int days) throws BadEntryException{
    if(days <= 0){
      throw new BadEntryException("invalid date");
    }
    _date.advanceDate(days);
  }



  public void registerSimpleProduct(String id){
    SimpleProduct prod = new SimpleProduct(id);
    _products.put(id, prod);
  }
  public void registerAggregateProduct(String idProduct, double aggravation,ArrayList<Component> components){
    AggregateProduct product = new AggregateProduct(idProduct, aggravation, components);
    _products.put(idProduct, product);
  }
  public Product getProduct(String id){
    return _products.get(id);
  }
  public HashMap<String, Product> getProductMap(){
    return _products;
  }
  public ArrayList<Product> getProductList(){
    return new ArrayList<Product>(_products.values());
  }
  public ArrayList<Product> getProductSortedList(){
    ArrayList<Product> productList = new ArrayList<>(_products.values());
    productList.sort(new ProductComparator());
    return productList;
  }



  public void registerBatch( double price, int stock,Partner partner,Product product){
    partner.registerBatch(price, stock, partner, product);
  }
  public ArrayList<Batch> getBatchList(){
    ArrayList<Batch> batches= new ArrayList<>();
    for(Product product : new ArrayList<>(_products.values()))
      for(Batch batch : product.getBatches())
        batches.add(batch);
    return batches;
  }
  public ArrayList<Batch> sortBatches(ArrayList<Batch> batches){
    batches.sort(new BatchComparator());
    return batches;
  }
  public ArrayList<Batch> getBatchSortedList(){
    return sortBatches(getBatchList());
  }
  public ArrayList<Batch> getBatchSortedList(Product product){
    return sortBatches(product.getBatches());
  }
  public ArrayList<Batch> getBatchSortedList(Partner partner){
    return sortBatches(partner.getBatches());
  }

  public void registerPartner(String name, String adress, String id) throws BadEntryException{
    if(_partner.contains(id.toLowercase()));
    for(String i : _partners.keySet())
      if(i.toLowerCase().equals(id.toLowerCase())) 
        throw new BadEntryException("Partner already exists");
    Partner partner = new Partner(name, adress, id);
    _partners.put(toLowerCase(id),partner);
  }
  public Partner getPartner(String id) throws BadEntryException{
    if(_partners.containsKey(id)){
      return _partners.get(id);
    } else{
      throw new BadEntryException("unknowPartner");
    }
  }
  public HashMap<String, Partner> getPartnerMap(){
    return _partners;
  }
  public ArrayList<Partner> getPartnerList(){
    return new ArrayList<>(_partners.values());
  }
  public ArrayList<Partner> getPartnerSortedList(){
    ArrayList<Partner> partnerList = getPartnerList();
    partnerList.sort(new PartnerComparator());
    return partnerList;
  }


  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
    Parser parser = new Parser(this);
    parser.parseFile(txtfile);
    }
}
