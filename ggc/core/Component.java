package ggc.core;

public class Component {
    private int _quantity;
    private Product _product;
    

    public Product getProduct(){
        return _product;
    }

    public int getQuantity(){
        return _quantity;
    }
}
