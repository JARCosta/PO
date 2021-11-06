package ggc.core;

public abstract class Sale extends Transaction {
  private Partner _Partner;
  
  Sale(Product product, int quantity, Partner partner){
    super(product, quantity);
    _Partner = partner;
  }
  public Partner getpartner(){
    return _Partner;
  }
}
