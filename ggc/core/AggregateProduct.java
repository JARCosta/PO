package ggc.core;

public class AggregateProduct extends Product {
    private Recipe _recipe;
    
    AggregateProduct(String id){
        super(id);
    }

    boolean checkQuantity(int quantity, Partner p){
        return super.getQuantity(p) >= quantity;
    }
    
    public int getQuantity(){
        return super.getQuantity();
    }

    @Override
    public String toString(){
        return getId() + "|" + getMaxPrice() + "|" + getQuantity() + "|" + _recipe.toString();
    }

}