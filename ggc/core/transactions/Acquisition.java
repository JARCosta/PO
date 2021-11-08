package ggc.core.transactions;

import ggc.core.partners.Partner;
import ggc.core.products.Product;

public class Acquisition extends Transaction{
  private Partner _partner;

  public Acquisition(Partner partner, Product product, int quantity, int transactionId){
    super(product, quantity,transactionId);
    _partner = partner;
  }

  @Override
  public String toString() {
    //COMPRA|id|idParceiro|idProduto|quantidade|valor-pago|data-pagamento
    return "COMPRA|" + super.getId() + "|" + _partner.getId() + "|" + super.getProduct().getId() + "|" + super.getQuantity() + "|" + super.getBaseValue() + "|" + super.getPaymentDate();
  }
}