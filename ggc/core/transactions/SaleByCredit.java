package ggc.core.transactions;

import ggc.core.Batch;
import ggc.core.Date;
import ggc.core.partners.Partner;
import ggc.core.products.Product;

public class SaleByCredit extends Sale{
  private Date _deadine;
  private double _amountPaid;

  public SaleByCredit(Partner partner, Product product, int quantity, int deadline, int transactionId){
    super(product, quantity, partner, transactionId);
    _deadine = new Date(deadline);
    removeQuantity(partner, product,quantity);
  }

  public void removeQuantity(Partner partner,Product product, int quantity){
    double baseValue=0;
    int quan = quantity;
    while(quan > 0){
      Batch removingBatch = product.searchCheapestBatch(partner);
      if(removingBatch.getQuantity() <= quan){
        //System.out.println("quantity"+quantity+" > batch quantity"+ removingBatch.getQuantity());
        quan -= removingBatch.getQuantity();
        baseValue += removingBatch.getPrice()*removingBatch.getQuantity();
        removeBatch(removingBatch);
      } else{
        //System.out.println("quantity"+quantity+" < batch quantity"+ removingBatch.getQuantity());
        baseValue += removingBatch.getPrice()*quan;
        quan = 0;
        removingBatch.removeQuantity(quan);
      }
    }
    super.setBaseValue(baseValue);
  }

  public void removeBatch(Batch batch){
    batch.getPartner().removeBatch(batch);
    batch.getProduct().removeBatch(batch);
  }

  public double getAmountPaied(){ return _amountPaid;}
  public double getAmountToPay(){
    //System.out.println(""+super.getBaseValue()+" "+super.getQuantity());
    return super.getBaseValue() - getAmountPaied();
  }
  
  public void pay(Date date){
    if(_amountPaid != super.getBaseValue())
      _amountPaid = super.getBaseValue();
      if(_deadine.getDate()>date.getDate()){
        super.getPartner().addPoints(_amountPaid*10);
        super.getPartner().updateStatus();
      }
      super.setPaymentDate(date);
  }
  
  @Override
  public boolean isPaid(){
    return _amountPaid == super.getQuantity()*super.getBaseValue();
  }

  public String toString(){
    String ret = "";
    if(super.getPaymentDate() != null){ ret = "|" + super.getPaymentDate(); }
    return "VENDA|" + super.getId() + "|" + super.getPartner().getId() + "|" + super.getProduct().getId() + "|" + super.getQuantity() + "|" + (int)super.getBaseValue() + "|" + (int)getAmountToPay() + "|" + _deadine + ret;
  }
}
