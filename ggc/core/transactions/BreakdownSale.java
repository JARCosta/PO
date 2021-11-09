package ggc.core.transactions;

import java.util.ArrayList;
import java.util.List;

import ggc.core.Batch;
import ggc.core.partners.Partner;
import ggc.core.products.AggregateProduct;
import ggc.core.products.Component;
import ggc.core.products.Product;

public class BreakdownSale extends Sale{
  List<Batch> _batches;
  public BreakdownSale(Product product, int quantity, Partner partner, int transactionId){
    super(product, quantity, partner,transactionId);
    _batches = new ArrayList<>();
    breakdown(partner,(AggregateProduct)product,quantity);
  }
  public List<Batch> getBatchList(){
    return _batches;
  }

  public void setBatcheList(Product product,Partner partner){
    _batches = product.getBatchSortedList(partner);
  }

  public void breakdown(Partner partner, AggregateProduct product, int quantity){
    int quant = quantity;
    while(quant > 0){
      Batch removingBatch = product.searchCheapestBatch(partner);
      if(removingBatch.getQuantity() <= quantity){
        //System.out.println("quantity"+quantity+" > batch quantity"+ removingBatch.getQuantity());
        quant -= removingBatch.getQuantity();
        //baseValue += removingBatch.getQuantity()*removingBatch.getPrice();
        for(Component i : product.getRecipe().getComponents()){
          partner.registerBatch(removingBatch.getPrice(), removingBatch.getQuantity(), i.getProduct());
        }
        partner.removeBatch(removingBatch);
        product.removeBatch(removingBatch);
      } else{
        //System.out.println("quantity"+quantity+" < batch quantity"+ removingBatch.getQuantity());
        //baseValue += quant*removingBatch.getPrice();
        for(Component i : product.getRecipe().getComponents()){
          partner.registerBatch(removingBatch.getPrice(), quant, i.getProduct());
        }
        removingBatch.removeQuantity(quant);
        quant = 0;
      }
    }
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
