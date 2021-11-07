package ggc.core;

public class Notification{
    private String _type;
    private String _productId;
    private double _productPrice;

    public Notification(String type, String productId, double productPrice){
        _type = type;
        _productId = productId;
        _productPrice = productPrice;
    }

    // desenhos : observer e strategy

    @Override
    public String toString(){
        return _type + "|" + _productId + "|" + _productPrice;
    }

    public String getType(){return _type;}
    public String getProductId(){return _productId;}
    public double getProductPrice(){return _productPrice;}



}