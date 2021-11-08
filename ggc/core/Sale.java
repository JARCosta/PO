package ggc.core;

public abstract class Sale extends Transaction {
  private Partner _Partner;
  
  Sale(Product product, int quantity, Partner partner, int transactionId){
    super(product, quantity,transactionId);
    _Partner = partner;
  }
  public Partner getPartner(){
    return _Partner;
  }
}
