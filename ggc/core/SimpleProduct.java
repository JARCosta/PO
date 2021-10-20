package ggc.core;

public class SimpleProduct extends Product{

    void SimpleProduct(String id){

    }

    int checkQuantity(int quantity){

    }

    @Override
    public String toString(){
        return _id + "|" + _maxPrice + "|" + "stock atual total";
    }
    
}
