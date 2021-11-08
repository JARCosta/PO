package ggc.core.products;

public class Component {
  private int _quantity;
  private Product _product;
  
  public Component(Product product, int quantity){
    _quantity = quantity;
    _product = product;
  }

  public Product getProduct(){
    return _product;
  }

  public int getQuantity(){
    return _quantity;
  }
}
