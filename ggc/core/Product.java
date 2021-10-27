package ggc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public abstract class Product implements Serializable{
  private double _maxPrice;
  private String _id;
  private List<Batch> _batches;
  Product(String id){
    _id = id;
    _maxPrice = 0;
    _batches = new ArrayList<Batch>();
  }
  public String toString(){
    return _id + "|" + (int)_maxPrice + "|" + getQuantity();
  }

  boolean checkQuantity(int quantity, Partner p){
    int total = 0;
    for(Batch batch : p.getBatches()){
      total += batch.getQuantity();
    }
    return total > quantity;
  }

  public String getId(){
    return _id;
  }

  public double getMaxPrice(){
    double maxPrice = 0;
    for(Batch i : _batches){
      maxPrice += i.getPrice();
    }
    return _maxPrice = maxPrice;
  }
  public int getQuantity(){
    int quantity = 0;
    for( Batch i : _batches){
      quantity += i.getQuantity();
    }
    return quantity;
  }

  public int getQuantity(Partner p){
    int quantity = 0;
    for( Batch i: _batches){
      if(i.getpartner() == p){
        quantity += i.getQuantity();
      }
    }
    return quantity;
  }

  public List<Batch> getBatches(){
    return _batches;
  }

  public ArrayList<Batch> getBatchSortedList(){
    ArrayList<Batch> batchSorted = (ArrayList<Batch>) _batches;
    batchSorted.sort(new BatchComparator());
    return batchSorted;
  }
  public void addBatch(Batch batch){
    _batches.add(batch);
  }

  @Override
  public int hashCode(){
    return Objects.hash(_id);
  }
  @Override
  public boolean equals(Object p2){
    return p2 instanceof Product && _id.equals(((Product) p2).getId());
  }
}