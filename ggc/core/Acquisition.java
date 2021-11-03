package ggc.core;

public class Acquisition extends Transaction{
  private Partner _partner;

  Acquisition(Product product, int quantity, Partner partner){
    super(product, quantity);
    _partner = partner;
  }

  @Override
  public String toString() {
    //COMPRA|id|idParceiro|idProduto|quantidade|valor-pago|data-pagamento
    return "COMPRA|" + super.getId() + "|" + _partner.getId() + "|" + super.getProduct().getId() + "|" + super.getQuantity() + "|" + super.getBaseValue() + "|" + super.getDate();
  }
}
