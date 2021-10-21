package ggc.core;

public class SimpleProduct extends Product{

    SimpleProduct(String id){
        super(id);
    }

    boolean checkQuantity(int quantity, Partner p){
        return super.getQuantity(p) >= quantity;
    }

    @Override
    public String toString(){
        return getId() + "|" + getMaxPrice() + "|" + "stock atual total";
    }
}
