package ggc.core;

public abstract class Product {
    private double _maxPrice;
    private String _id;
    private Batch _batch;

    void Product(String id){
        _id = id;
        _maxPrice = 0;
    }

    public String toString(){
        return _id + "|" + _maxPrice + "|" + "stock-atual-total";
    }

    int checkQuantity(int quantity, Partner p){ 
        return _batch.getQuantity();
    }

    public String getId(){
        return _id;
    }

    public double getMaxPrice(){
        return _maxPrice;
    }
    
    


}
