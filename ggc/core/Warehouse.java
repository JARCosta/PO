package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import ggc.core.exception.BadEntryException;

/**
 * Class Warehouse implements a warehouse.
 */
public class Warehouse implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202109192006L;
  private Date _date;
  private Map<String, Partner> _partnersMap;
  private ArrayList<Product> _products;
  

  public Warehouse(){
    _date = new Date(0);
    _partnersMap = new HashMap<String, Partner>();
  }
  

  public int currentDate(){
    return _date.currentDate();
  }

  public void advanceDate(int days){
    _date.advanceDate(days);
  }

  public void registerPartner(String name, String Adress, String id){
    Partner partner = new Partner(name, Adress, id);
    _partnersMap.put(id,partner);
  }
/*
  public ArrayList<Partner> getPartnerList(){
    return _partners;
  }
  */

  public Map<String, Partner> getPartners(){
    return _partnersMap;
  }

  public Partner getPartner(String id){
    return _partnersMap.get(id);
  }

  public void registerSimpleProduct(String id){
    SimpleProduct prod = new SimpleProduct(id);
    _products.add(prod);
  }

  public ArrayList<Product> getProducts(){
    return _products;
  }

  public Product getProduct(String id){
    for( Product i : _products){
      if(i.getId() == id);
      return i;
    }
    return null;
  }

  public void registerAggregateProduct(String idProduct, double aggravation,ArrayList<Product> products,ArrayList<Integer> quantities){
    AggregateProduct product = new AggregateProduct(idProduct, aggravation, products, quantities);
  }

  public void registerBatch( double price, int stock,Partner partner,Product product){
    partner.registerBatch(price, stock, partner, product);
  }



  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }

}
