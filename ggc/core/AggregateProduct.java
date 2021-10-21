package ggc.core;

import java.util.ArrayList;

public class AggregateProduct extends Product {
    
    private Recipe _recipe;
    private double _aggravation;
    
    AggregateProduct(String id, double aggravation,ArrayList<Product> products,ArrayList<Integer> quantities){
        super(id);
        _recipe = new Recipe(aggravation, products, quantities);
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