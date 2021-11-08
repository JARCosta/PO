package ggc.core.partners;

public abstract class PartnerState {
    private Partner _partner;
    private String _status;

    public PartnerState(Partner partner, String status){
        _partner = partner;
        _status = status;
    }
    
//    void normal();
//    void selection();
//    void ElitePartner();

}
