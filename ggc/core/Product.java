package ggc.core;

import java.util.ArrayList;

public abstract class Product {
    private double _maxPrice;
    private String _id;
    private ArrayList<Batch> _batches;

    Product(String id){
        _id = id;
        _maxPrice = 0;
        _batches = new ArrayList<Batch>();
    }

    public String toString(){
        return _id + "|" + (int)_maxPrice + "|" + getQuantity();
    }

    boolean checkQuantity(int quantity, Partner p){ 
        return false;
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

    public ArrayList<Batch> getBatches(){
        return _batches;
    }
    public ArrayList<Batch> getBatchSortedList(){
        ArrayList<Batch> batchSorted = _batches;
        batchSorted.sort(new BatchComparator());
        return batchSorted;
    }
    public void addBatch(Batch batch){
        _batches.add(batch);

    }
}