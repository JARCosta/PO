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
  //private List<Notification> _notificationsHistory;
  private Map<Product, List<Notification>> _notificationsByProduct;
  //private List<Notification> _relevantNotifications;


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
    //_notificationsHistory = new ArrayList<Notification>();
    _notificationsByProduct = new HashMap<Product, List<Notification>>();
    //_relevantNotifications = _notificationsHistory;
  }

  public String getId(){
    return _id;
  }

  @Override
  public String toString() {
    return _id + "|" + _name + "|" + _adress + "|" + _state.getStatus() + "|" + (int)_points + "|" + (int)_valorCompras + "|" + (int)_valorVendas + "|" + (int)_valorVendasPagas;
  }
  // NOTIFICATION

  public void addNotificationByProduct(Notification notif){
    List<Notification> productNotifs = _notificationsByProduct.get(notif.getProduct());
    if(productNotifs == null){
      productNotifs = new ArrayList<Notification>();
      productNotifs.add(notif);
      _notificationsByProduct.put(notif.getProduct(), productNotifs);
    }else{
      if(!productNotifs.contains(notif)){
        productNotifs.add(notif);
      }
    }
  }

  public void addNotification(Notification notif){
    //_notificationsHistory.add(notif);
    addNotificationByProduct(notif);
  }

  public void clearNotifications(){
    //_notificationsHistory.clear();
    for(List<Notification> productNotifs: _notificationsByProduct.values()){
      productNotifs.clear();
    }
  }

  public String showNotifications(){
    String notifs = "";
    for(Notification notif: _notificationsByProduct.values()){
      notifs += notif.toString();
    }
    return notifs;
  }

  
  public boolean toggleNotifications(Product product){
    

  }
  /*
  public void toggleNotificationsOn(Product product){

  }

  public void toggleNotificationsOff(Product product){


  }
  */

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