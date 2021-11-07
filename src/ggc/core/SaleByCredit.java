package ggc.core;

public class SaleByCredit extends Sale{
  private Date _deadine;
  private double _amountPaid;
  private double _baseValue;

  public SaleByCredit(Partner partner, Product product, int quantity, int deadline){
    super(product, quantity, partner);
    _deadine = new Date(deadline);


    Batch removingBatch = product.searchCheapestBatch();
    while(quantity > 0){
      if(removingBatch.getQuantity()<quantity){
      quantity -= removingBatch.getQuantity();
      _baseValue += removingBatch.getQuantity()*removingBatch.getPrice();
      removeBatch(removingBatch);
      } else{
      _baseValue += quantity*removingBatch.getPrice();
      quantity-= removingBatch.getQuantity();
      removingBatch.removeQuantity(quantity);
      }
    }

  }

  public double getAmountToPay(){
    return super.getBaseValue() - _amountPaid; // should be total amopunt to pay - _amount paied;
  }




  public String toString(){
    String ret = "";
    if(super.getPaymentDate() != null){ ret = "|" + super.getPaymentDate(); }
    return "VENDA|" + super.getId() + "|" + super.getPartner().getId() + "|" + super.getProduct().getId() + "|" + _deadine + "|" + (int)super.getBaseValue() + "|" + (int)getAmountToPay() + "|" + super.getQuantity() + ret;
  }
}
