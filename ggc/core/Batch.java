package ggc.core;

public class Batch {
    private double _price;
    private int _quantity;
    private Product _product;
    private Partner _partner;


    public Batch(double price, int stock,Partner partner, Product product){
        _price = price;
        _quantity = stock;
        _product = product;
        _partner = partner;
    }

    public String toString(){
        return _product.getId() + "|" + _partner.getId() + "|" + _price + "|" + _quantity;
    }

    public int getQuantity(){
        return _quantity;
    }

    public Partner getpartner(){
        return _partner;
    }


//dsadasdsadsad


}
