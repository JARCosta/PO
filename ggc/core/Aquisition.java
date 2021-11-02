package ggc.core;

public class Aquisition extends Transaction{

  Aquisition(Product product, int quantity, Partner partner){
    super(quantity);
    partner.registerAquisition(this);
  }

  @Override
  public String toString() {
    return "COMPRA|" + super.getId();
  }
}
