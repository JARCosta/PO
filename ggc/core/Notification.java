package ggc.core;

public class Notification{
    private String _type;
    private Product _product;
    private double _productPrice;

    public Notification(String type, Product product, double productPrice){
        _type = type;
        _product = product;
        _productPrice = productPrice;
    }

    // desenhos : observer e strategy

    @Override
    public String toString(){
        return _type + "|" + _product.getId() + "|" + _productPrice;
    }

    public String getType(){return _type;}
    public String getProductId(){return _product.getId();}
    public Product getProduct(){return _product;}
    public double getProductPrice(){return _productPrice;}



}