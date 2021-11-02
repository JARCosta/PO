package ggc.core;

public class Aquisition extends Transaction{
  private Partner _partner;

  Aquisition(Product product, int quantity, Partner partner){
    super(product, quantity);
    _partner = partner;
    partner.registerAquisition(this);
  }

  @Override
  public String toString() {
    return "COMPRA|" + super.getId();
  }
}
