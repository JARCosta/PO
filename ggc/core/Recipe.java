package ggc.core;

import java.util.ArrayList;

public class Recipe {
    private double _alpha;
    ArrayList<Component> _components;



    public Recipe(double alpha, ArrayList<Component> components){
        _alpha = alpha;
        _components = components;
    }

/* may be useless
    public Recipe(double alpha,ArrayList<Product> products, ArrayList<Integer> quantities){
        _alpha = alpha;
        _components = new ArrayList<Component>();
        for(int i = 0;i < products.size(); i++){
            Product prod = products.get(i);
            int quan = quantities.get(i);
            Component comp = new Component(prod, quan);
            _components.add( comp );
        }
    }
*/

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
