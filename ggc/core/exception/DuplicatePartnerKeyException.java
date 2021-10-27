package ggc.core.exception;

public class DuplicatePartnerKeyException extends Exception {
  private String _duplicateId;
  
  public DuplicatePartnerKeyException(String id) {
    _duplicateId = id;
  }

  public String getInvalidId() {
    return _duplicateId;
  }
}
