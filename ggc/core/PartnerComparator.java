package ggc.core;

import java.util.Comparator;

class PartnerComparator implements Comparator<Partner> {
    public int compare(Partner a, Partner b){
        return a.getId().compareTo(b.getId());
    }

}