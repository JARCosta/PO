package ggc.core;

import java.io.Serializable;

public class Batch implements Serializable{
    /**
     * Batch's price per Product unit
     */
    private double _price;
    /**
     * Batch's quantity of product
     */
    private int _quantity;
    /**
     * Batch's product
     */
    private Product _product;
        /**
     * Batch's partner
     */
    private Partner _partner;

    /**
     * 
     * 
     * @param price
     * @param stock
     * @param partner
     * @param product
     */
    public Batch(double price, int stock,Partner partner, Product product){
        _price = price;
        _quantity = stock;
        _product = product;
        _partner = partner;
    }

    @Override
    public String toString(){
        return _product.getId() + "|" + _partner.getId() + "|" + (int)_price + "|" + _quantity;
    }

    public int getQuantity(){
        return _quantity;
    }

    public double getPrice(){
        return _price;
    }

    public Partner getpartner(){
        return _partner;
    }

    public Product getProduct(){
        return _product;
    }
}
