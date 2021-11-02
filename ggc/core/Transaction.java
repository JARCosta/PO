package ggc.core;

import java.sql.Date;

public abstract class Transaction {
  private int _id;
  private Date _paymentDate;
  private double _baseValue;
  private int _quantity;

  Transaction(int quantity){
    _quantity = quantity;
  }

  public int getId(){return _id;}
  public int getQuantity(){return _quantity;}
  public double getBaseValue(){return _baseValue;}
  public Date getDate(){return _paymentDate;}

  public String toString(){
    return _id +"|"+ _quantity + "|" + _baseValue + "|" + _paymentDate;
    }

}
