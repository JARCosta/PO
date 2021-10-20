package ggc.core;

import java.util.ArrayList;

public class Batch {
    private double _price;
    private int _quantity;
    private Product _product;
    private Partner _partner;
    private ArrayList<Product> _products;


    public String toString(){
        return _product.getId() + "|" + _partner.getId() + "|" + _price + "|" + _quantity;
    }

    public int getQuantity(){
        return _quantity;
    }
    
}
