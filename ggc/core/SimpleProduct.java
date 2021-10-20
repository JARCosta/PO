package ggc.core;

public class SimpleProduct extends Product{

    void SimpleProduct(String id){
        

    }

    @Override
    int checkQuantity(int quantity, Partner p){
        return 0;
    }

    @Override
    public String toString(){
        return getId() + "|" + getMaxPrice() + "|" + "stock atual total";
    }
    
}
