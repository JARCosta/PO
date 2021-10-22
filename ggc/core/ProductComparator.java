package ggc.core;

import java.util.Comparator;

class ProductComparator implements Comparator<Product> {
    public int compare(Product p1, Product p2){
        return p1.getId().compareTo(p2.getId());
    }
}
