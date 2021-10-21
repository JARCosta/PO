package ggc.core;

// FIXME import classes (cannot import from pt.tecnico or ggc.app)
import java.io.Serializable;
import java.util.ArrayList;
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
    _date = new Date(0);
    _partners = new HashMap<String, Partner>();
  }
  


  public int currentDate(){
    return _date.currentDate();
  }

  public void advanceDate(int days){
    _date.advanceDate(days);
  }



  public void registerPartner(String name, String Adress, String id){
    Partner partner = new Partner(name, Adress, id);
    _partners.put(id,partner);
  }

  public void registerSimpleProduct(String id){
    SimpleProduct prod = new SimpleProduct(id);
    _products.put(id, prod);
  }

  public void registerAggregateProduct(String idProduct, double aggravation,ArrayList<Component> components){
    AggregateProduct product = new AggregateProduct(idProduct, aggravation, components);
  }

  public void registerBatch( double price, int stock,Partner partner,Product product){
    partner.registerBatch(price, stock, partner, product);
  }



  public Map<String, Partner> getPartners(){
    return _partners;
  }

  public HashMap<String, Product> getProductMap(){
    return _products;
  }

  public Partner getPartner(String id){
    return _partners.get(id);
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

  }
}
