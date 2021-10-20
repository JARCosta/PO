package ggc.core;

public abstract class Product {
    private double _maxPrice;
    private String _id;

    void Product(String id){
        _id = id;
        _maxPrice = 0;
    }

    public String toString(){
        return _id + "|" + _maxPrice + "|" + "stock atual total";
    }

    abstract int checkQuantity(int quantity, Partner p){
    }
    
    


}
