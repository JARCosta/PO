package ggc.core;

import java.util.ArrayList;

public abstract class Product {
    private double _maxPrice;
    private String _id;
    private ArrayList<Batch> _batches;

    Product(String id){
        _id = id;
        _maxPrice = 0;
    }

    public String toString(){
        return _id + "|" + (int)_maxPrice + "|" + "stock-atual-total";
    }

    boolean checkQuantity(int quantity, Partner p){ 
        return false;
    }

    public String getId(){
        return _id;
    }

    public double getMaxPrice(){
        return _maxPrice;
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
/*    public ArrayList<Batch> getBatchSortedList(){
        ArrayList<Batch> batchSorted = _batches;
        batchSorted.sort(new BatchComparator());
        return batchSorted;
    }*/
}
