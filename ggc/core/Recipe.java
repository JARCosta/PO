package ggc.core;

import java.util.ArrayList;

public class Recipe {
    private double _alpha;
    ArrayList<Component> _components;

    public ArrayList<Component> getComponents(){
        return _components;
    }

    public String toString(){
        String ret = "";
        for( Component i : _components){
            Product p = i.getProduct();
            if(ret != "")
                ret += "#";
            ret += "" + p.getId() + ":" + i.getQuantity();
        }
        return ret;
    }
    
}
