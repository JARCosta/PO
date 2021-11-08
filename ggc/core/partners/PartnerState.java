package ggc.core.partners;

import java.io.Serializable;

public abstract class PartnerState implements Serializable {
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
