package ggc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

  public Partner(String id, String name, String adress){
    _name = name;
    _adress = adress;
    _id = id;
    _state = new NormalPartner(this);
    _points = 0;
    _batches = new ArrayList<>();
    _acquisitions = new ArrayList<>();
    _sales = new ArrayList<>();
    _notifications = new ArrayList<>();
  }

  public String getId(){
    return _id;
  }

  @Override
  public String toString() {
    return _id + "|" + _name + "|" + _adress + "|" + _status + "|" + (int)_points + "|" + (int)_valorCompras + "|" + (int)_valorVendas + "|" + (int)_valorVendasPagas;
  }

  public String showNotifications(){
    String notifs = "";
    for(Notification notif: _notifications){
      notifs += notif.toString();
    }
    return notifs;
  }

  public void addNotification(String type, Product product){
    Notification notif = new Notification(type, product.getId(), product.getMaxPrice());
    _notifications.add(notif);
  }
  

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

  public void registerBatch(double price, int stock, Partner partner, Product product){
    Batch batch = new Batch(price, stock, partner, product);
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
  public void registerAcquisition(Product product, int quantity){
    Acquisition acquisition = new Acquisition(this, product, quantity);
    _acquisitions.add(acquisition);
  }

  @Override
  public int hashCode(){
    return Objects.hash(_id);
  }
  @Override
  public boolean equals(Object p2){
    return p2 instanceof Partner && _id.equals(((Product) p2).getId());
  }
}