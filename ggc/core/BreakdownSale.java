package ggc.core;

import java.util.ArrayList;
import java.util.List;

public class BreakdownSale extends Sale{
    List<Batch> _batches;
    public BreakdownSale(Product product, int quantity, Partner partner, int transactionId){
        super(product, quantity, partner,transactionId);
        _batches = new ArrayList<>();
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
