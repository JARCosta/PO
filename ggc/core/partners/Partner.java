package ggc.core.partners;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Map;

import ggc.core.Batch;
import ggc.core.Notification;
import ggc.core.products.Product;
import ggc.core.transactions.Acquisition;
import ggc.core.transactions.BreakdownSale;
import ggc.core.transactions.Sale;
import ggc.core.transactions.SaleByCredit;

public class Partner implements Serializable{
  private String _name;
  private String _adress;
  private String _id;
  private String _status;
  private double _points;
  private double _valorCompras;
  private double _valorVendas;
  private double _valorVendasPagas;
  private ArrayList<Batch> _batches;
  private List<Acquisition> _acquisitions;
  private List<Sale> _sales;
  private List<Notification> _notifications;
  private List<Notification> _relevantNotifications;
  private Map<Product, String> _relevantProducts;

  public Partner(String id, String name, String adress){
    _name = name;
    _adress = adress;
    _id = id;
    _status= "NORMAL";
    _points = 0;
    _batches = new ArrayList<Batch>();
    _acquisitions = new ArrayList<Acquisition>();
    _sales = new ArrayList<Sale>();
     _notifications = new ArrayList<>();
    _relevantNotifications = new ArrayList<>();
    _relevantProducts = new HashMap<Product, String>();
  }

  public String getId(){
    return _id;
  }

//BATCHES--------------------------------------------------------------------------------------------------------
  
  public void registerBatch(double price, int stock, Product product){
    Batch batch = new Batch(price, stock, this, product);
    _batches.add(batch);
  }
  public void removeBatch(Batch batch){
    _batches.remove(batch);
  }
  public List<Batch> getBatches(){
    return _batches;
  }
  public List<Batch> getBatches(Product p){
    ArrayList<Batch> ret = new ArrayList<>();
    for(Batch b : _batches){
      if(b.getProduct().equals(p)){
        ret.add(b);
      }
    }
    return ret;
  }


//TRANSACTION--------------------------------------------------------------------------------------------------------

  public void registerAcquisition(Acquisition acq,double price){
    //System.out.println(price);
    _acquisitions.add(acq);
    acq.setBaseValue(price);
    _valorCompras += acq.getBaseValue()*acq.getQuantity();
    //System.out.println("A"+price + " "+ acq.getQuantity());
  }
  public void registerSaleByCredit(SaleByCredit sale){
    //System.out.println(sale.getAmountToPay());
    _sales.add(sale);
  }
  public void registerBreakSownSale(BreakdownSale sale){
    _sales.add(sale);
  }

  public List<Acquisition> getAcquisitionList(){
    return _acquisitions;
  }

  public List<Sale> getSaleList(){
    return _sales;
  }


// NOTIFICATION--------------------------------------------------------------------------------------------------------

  public void addNotification(Notification notif){
    if(notif.getType().equals("NEW")){
      _relevantProducts.put(notif.getProduct(), "true");
      _notifications.add(notif);
      _relevantNotifications.add(notif);
    }
    if(notif.getType().equals("BARGAIN")){
      if(_relevantProducts.containsKey(notif.getProduct()) && _relevantProducts.get(notif.getProduct()).equals("false")){
        _notifications.add(notif);
      }
      /*
      else if(_relevantProducts.containsKey(notif.getProduct()) && _relevantProducts.get(notif.getProduct()).equals("true")){
        _notifications.add(notif);
        _relevantNotifications.add(notif);
      }
      */
      else{
        _notifications.add(notif);
        _relevantNotifications.add(notif);
      }
    }
  }

  //used
  public void clearNotifications(){
    _notifications.clear();
    _relevantNotifications.clear();
  }


  //used
  public Collection<Notification> showNotifications(){
    return _relevantNotifications;
  }

  //used
  public void toggleNotifications(Product product){
    if(_relevantProducts.containsKey(product)){
      if(_relevantProducts.get(product).equals("true")) {toggleNotificationsOff(product);}
      if(_relevantProducts.get(product).equals("false")) {toggleNotificationsOn(product);}
    }
  }

  //used
  public void toggleNotificationsOn(Product product){
    List<Notification> toggledNotifications = new ArrayList<>();
    for(Notification notif: _notifications){
      if(notif.getProduct().equals(product) || _relevantNotifications.contains(notif)){
        toggledNotifications.add(notif);        
      }
    }
    _relevantProducts.put(product, "true");
    _relevantNotifications = toggledNotifications;
  }
  
  //used
  public void toggleNotificationsOff(Product product){
    List<Notification> toggledNotifications = new ArrayList<>();
    for(Notification notif: _relevantNotifications){
      if(!notif.getProduct().equals(product)){
        toggledNotifications.add(notif);
      }
    }
    _relevantProducts.put(product, "false");
    _relevantNotifications = toggledNotifications;
  }

  public void setNotifications(Collection<Notification> notifs){
    for(Notification notif: notifs){
      _notifications.add(notif);
      _relevantNotifications.add(notif);
    }
  }

//STATUS--------------------------------------------------------------------------------------------------------

  public double getPoints(){
    return _points;
  }
  public void addPoints(double adding){
    _points += adding;
  }
  public void multiplyPoints(double value){
    _points = _points*value;
  }

  // desenho: state + singleton
  public void updateStatus(){
    if(_points>2000)
      if(_points>25000)
      _status = "ELITE";
      else
      _status = "SELECTION";
    else
    _status = "NORMAL";
    //System.out.println(_status);
  }

  public void calculateAlpha(int dateDifference){
    if(_status == "ELITE"){
      if(dateDifference<0){
        multiplyPoints(0.25);
        _status="SELECTION";
      }
    }
    if(_status == "SELECTION"){
      if(dateDifference<-2){
        multiplyPoints(0.1);
        _status="NORMAL";
      }
    }
    if(_status == "NORMAL")
      if(dateDifference<0)
        multiplyPoints(0);
  }


//---------------------------------------------------------------------------------------------------------------------

  @Override
  public int hashCode(){
    return Objects.hash(_id);
  }
  @Override
  public boolean equals(Object p2){
    return p2 instanceof Partner && _id.equals(((Partner) p2).getId());
  }
  @Override
  public String toString() {
    return _id + "|" + _name + "|" + _adress + "|" + _status + "|" + (int)_points + "|" + (int)_valorCompras + "|" + (int)_valorVendas + "|" + (int)_valorVendasPagas;
  }
}