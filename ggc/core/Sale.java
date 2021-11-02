package ggc.core;

public abstract class Sale extends Transaction {
  Sale(Product product, int quantity, Partner partner){
    super(quantity);
    partner.registerSale(this);
  }
}
