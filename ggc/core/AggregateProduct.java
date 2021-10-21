package ggc.core;

public class AggregateProduct extends Product {
    
    AggregateProduct(String id){
        super(id);
    }

    boolean checkQuantity(int quantity, Partner p){
        return super.getQuantity(p) >= quantity;
    }

    @Override
    public String toString(){
        return getId() + "|" + getMaxPrice() + "|" + "stock atual total" + "|" + "componente-1:quantidade-1#...#componente-n:quantidade-n";
    }

}