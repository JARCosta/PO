package ggc.core.transactions;

import java.util.List;

import ggc.core.Batch;
import ggc.core.partners.Partner;
import ggc.core.products.Product;

public class BreakdownSale extends Sale{
  List<Batch> _batches;
  public BreakdownSale(Product product, int quantity, Partner partner, int transactionId){
    super(product, quantity, partner,transactionId);
    _batches = product.getBatchSortedList(partner);
  }
  public List<Batch> getBatchList(){
    return _batches;
  }



  @Override
  public String toString() {
    return super.toString();
  }
}
