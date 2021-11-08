package ggc.core.transactions;

import ggc.core.partners.Partner;
import ggc.core.products.Product;

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
