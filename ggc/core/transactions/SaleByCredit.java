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
        //System.out.println(quan + " " + removingBatch.getQuantity());
        //System.out.println("quantity"+quantity+" < batch quantity"+ removingBatch.getQuantity());
        baseValue += removingBatch.getPrice()*quan;
        removingBatch.removeQuantity(quan);
        quan = 0;
      }
    }
    super.setBaseValue(baseValue);
  }

  public void removeBatch(Batch batch){
    batch.getPartner().removeBatch(batch);
    batch.getProduct().removeBatch(batch);
  }

  public double getAmountPaied(){ return _amountPaid;}
  public double getAmountToPay(Date date){
    return super.getBaseValue() * super.getPartner().calculateP(_deadine,date, getProduct());
  }
  
  public void pay(Date date){
    if(_amountPaid == 0)
      _amountPaid = getAmountToPay(date);

      if(_deadine.getDate()>date.getDate()){
        super.getPartner().addPoints(_amountPaid*10);
        super.getPartner().updateStatus();
      }
      super.setPaymentDate(date);
  }
  
  @Override
  public boolean isPaid(){
    //System.out.println(_amountPaid);
    return _amountPaid != 0;
  }

  public String toString(Date now){
    String ret = "";
    if(super.getPaymentDate() != null){ ret = "|" + super.getPaymentDate(); }
    return "VENDA|" + super.getId() + "|" + super.getPartner().getId() + "|" + super.getProduct().getId() + "|" + super.getQuantity() + "|" + (int)super.getBaseValue() + "|" + (int)getAmountToPay(now) + "|" + _deadine + ret;
  }
}