package ggc.core;

import java.util.ArrayList;

public class AggregateProduct extends Product {
    private Recipe _recipe;
    
    AggregateProduct(String id, double aggravation,ArrayList<Component> components){
        super(id);
        _recipe = new Recipe(aggravation, components);
    }

    boolean checkQuantity(int quantity, Partner p){
        return super.getQuantity(p) >= quantity;
    }
    
    public int getQuantity(){
        return super.getQuantity();
    }

    @Override
    public String toString(){
        return getId() + "|" + (int)getMaxPrice() + "|" + getQuantity() + "|" + _recipe.toString();
    }

}