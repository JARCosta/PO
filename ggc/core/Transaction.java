package ggc.core;

import java.sql.Date;

public class Transaction {
  private int _id;
  private Date _paymentDate;
  private double _baseValue;
  private int _quantity;

  public String toString(){
    return _id +"|"+ _quantity + "|" + _baseValue + "|" + _paymentDate;
    }

}
