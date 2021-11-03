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
  private ArrayList<Batch> _batches;
  private List<Acquisition> _acquisitions;
  private List<Sale> _sales;

  public Partner(String id, String name, String adress){
    _name = name;
    _adress = adress;
    _id = id;
    _status = "NORMAL";
    _points = 0;
  }

  public String getId(){
    return _id;
  }

  @Override
  public String toString() {
    return _id + "|" + _name + "|" + _adress + "|" + _status + "|" + (int)_points + "|" + (int)_valorCompras + "|" + (int)_valorVendas + "|" + (int)_valorVendasPagas;
  }

  public double getPoints(){
    return _points;
  }

  public void addPoints(double adding){
    _points += adding;
  }

  public void updateStatus(){
    if(_points>2000)
      if(_points>25000)
        _status = "ELITE";
      else
      _status = "SELECTION";
    else
    _status = "NORMAL";
  }

  public void registerBatch(double price, int stock, Partner partner, Product product){
    Batch batch = new Batch(price, stock, partner, product);
    _batches.add(batch);
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
    Acquisition acquisition = new Acquisition(product, quantity, this);
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