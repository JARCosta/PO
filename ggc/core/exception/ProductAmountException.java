package ggc.core.exception;

public class ProductAmountException extends Exception {
  private String _productId;
  private int _quantity;
  
  public ProductAmountException(String productId, int quantity) {
    _productId = productId;
    _quantity = quantity;

  }
  public int getQuantity() {
    return _quantity;
  }
  public String getProductId(){
    return _productId;
  }

}
