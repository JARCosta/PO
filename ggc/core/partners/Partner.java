package ggc.core.partners;

import java.io.Serializable;
import java.util.ArrayList;
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
import ggc.core.transactions.Transaction;

public class Partner implements Serializable{
  private String _name;
  private String _adress;
  private String _id;
  private String _status;
  private double _points;
  private double _valorCompras;
  private double _valorVendas;
  private double _valorVendasPagas;
  private PartnerState _state;
  private ArrayList<Batch> _batches;
  private List<Acquisition> _acquisitions;
  private List<Sale> _sales;
  private List<Notification> _notifications;
  private List<Notification> _relevantNotifications;
  private Map<Product, String> _relevantProducts;


//  private Map<Product, List<Notification>> _notifications;


  public Partner(String id, String name, String adress){
    _name = name;
    _adress = adress;
    _id = id;
    _state = new NormalPartner();
    _points = 0;
    _batches = new ArrayList<Batch>();
    _acquisitions = new ArrayList<Acquisition>();
    _sales = new ArrayList<Sale>();
    _notifications = new ArrayList<Notification>();
    _relevantNotifications = new ArrayList<>();
    _relevantProducts = new HashMap<Product, String>();
  }

  public String getId(){
    return _id;
  }

  @Override
  public String toString() {
    return _id + "|" + _name + "|" + _adress + "|" + _state.getStatus() + "|" + (int)_points + "|" + (int)_valorCompras + "|" + (int)_valorVendas + "|" + (int)_valorVendasPagas;
  }
  // NOTIFICATION

  public void addNotification(Notification notif){
    if(notif.getType().equals("NEW")){
      _relevantProducts.put(notif.getProduct(), "true");
      _notifications.add(notif);
      _relevantNotifications.add(notif);
    }
    if(notif.getType().equals("BARGAIN")){
      if(_relevantProducts.get(notif.getProduct()).equals("false")){
        _notifications.add(notif);
      }
      else{
        _notifications.add(notif);
        _relevantNotifications.add(notif);
      }
    }
  }

  public void clearNotifications(){
    _notifications.clear();
    _relevantNotifications.clear();
  }

  public String showNotifications(){
    String notifs = "";
    for(Notification notif: _relevantNotifications){
      notifs += notif.toString();
    }
    return notifs;
  }

  public void toggleNotifications(Product product){
    if(_relevantProducts.get(product).equalsIgnoreCase("true")) {toggleNotificationsOff(product);}
    if(_relevantProducts.get(product).equalsIgnoreCase("false")) {toggleNotificationsOn(product);}
  }

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
  

  //

  public double getPoints(){
    return _points;
  }

  public void addPoints(double adding){
    _points += adding;
  }

  public void updateStatus(){
    // desenho: state + singleton
//    if(_points>2000)
//      if(_points>25000)
//        _state.elite();
//      else
//      _state.selection();
//    else
//    _state.normal();
  }

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


//TRANSACTION
  public void registerAcquisition(Acquisition acq){
    _acquisitions.add(acq);
  }
  public void registerSaleByCredit(SaleByCredit sale){
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

  @Override
  public int hashCode(){
    return Objects.hash(_id);
  }
  @Override
  public boolean equals(Object p2){
    return p2 instanceof Partner && _id.equals(((Partner) p2).getId());
  }
}