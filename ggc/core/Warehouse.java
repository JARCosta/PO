package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
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



  public void registerPartner(String name, String adress, String id) throws BadEntryException{
    
    for(String i : _partners.keySet()){
      if(i.toLowerCase().equals(id.toLowerCase())){
        throw new BadEntryException("Partner already exists");
      }
    }
    
    Partner partner = new Partner(name, adress, id);
    _partners.put(id,partner);
  }

  public void registerSimpleProduct(String id){
    SimpleProduct prod = new SimpleProduct(id);
    _products.put(id, prod);
  }

  public void registerAggregateProduct(String idProduct, double aggravation,ArrayList<Component> components){
    AggregateProduct product = new AggregateProduct(idProduct, aggravation, components);
    _products.put(idProduct, product);
  }

  public void registerBatch( double price, int stock,Partner partner,Product product){
    partner.registerBatch(price, stock, partner, product);
  }



  public Map<String, Partner> getPartners(){
    return _partners;
  }

  public ArrayList<Product> getProductSortedList(){
    Collection<Product> products = _products.values();
    ArrayList<Product> productList = new ArrayList<>(products);
    productList.sort(new ProductComparator());
    return productList;
  }

  public ArrayList<Partner> getPartnerSortedList(){
    Collection<Partner> partners = _partners.values();
    ArrayList<Partner> partnerList = new ArrayList<>(partners);
    partnerList.sort(new PartnerComparator());
    return partnerList;
  }

  public HashMap<String, Product> getProductMap(){
    return _products;
  }

  public Partner getPartner(String id) throws BadEntryException{
    if(_partners.containsKey(id)){
      return _partners.get(id);
    } else{
      throw new BadEntryException("unknowPartner");
    }
  }

  public Product getProduct(String id){
    return _products.get(id);
  }


  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
    Parser parser = new Parser(this);
    //parser.
  }
}
