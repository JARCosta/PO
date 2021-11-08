package ggc.core.transactions;

import ggc.core.Batch;
import ggc.core.Date;
import ggc.core.partners.Partner;
import ggc.core.products.Product;

public class SaleByCredit extends Sale{
  private Date _deadine;
  private double _amountPaid;
  private double _baseValue;

  public SaleByCredit(Partner partner, Product product, int quantity, int deadline, int transactionId){
    super(product, quantity, partner, transactionId);
    _deadine = new Date(deadline);
    removeQuantity(product,quantity);
  }

  public void removeQuantity(Product product, int quantity){
    while(quantity > 0){
      
      Batch removingBatch = product.searchCheapestBatch();

      if(removingBatch.getQuantity() <= quantity){
        //System.out.println("quantity"+quantity+" > batch quantity"+ removingBatch.getQuantity());
        quantity -= removingBatch.getQuantity();
        _baseValue += removingBatch.getQuantity()*removingBatch.getPrice();
        removeBatch(removingBatch);
      } else{
        //System.out.println("quantity"+quantity+" < batch quantity"+ removingBatch.getQuantity());
        _baseValue += quantity*removingBatch.getPrice();
        quantity = 0;
        removingBatch.removeQuantity(quantity);
      }
    }
  }

  public void removeBatch(Batch batch){
    batch.getPartner().removeBatch(batch);
    batch.getProduct().removeBatch(batch);
  }

  public double getAmountToPay(){
    return super.getBaseValue() - _amountPaid; // should be total amopunt to pay - _amount paied;
  }




  public String toString(){
    String ret = "";
    if(super.getPaymentDate() != null){ ret = "|" + super.getPaymentDate(); }
    return "VENDA|" + super.getId() + "|" + super.getPartner().getId() + "|" + super.getProduct().getId() + "|" + super.getQuantity() + "|" + (int)super.getBaseValue() + "|" + (int)getAmountToPay() + "|" + _deadine + ret;
  }
}
