package ggc.core.partners;

public abstract class PartnerState {
  private String _status;

  public PartnerState(String status){
    _status = status;
  }
  public String getStatus(){
    return _status;
  }
  
//    void normal();
//    void selection();
//    void ElitePartner();

}
