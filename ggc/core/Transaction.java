package ggc.core;

import java.sql.Date;

public abstract class Transaction {
  private int _id;
  private Date _paymentDate;
  private double _baseValue;
  private int _quantity;
  private Product _product;

  Transaction(Product product,int quantity){
    _quantity = quantity;
    _product = product;
    _paymentDate = null;
  }

  public int getId(){return _id;}
  public int getQuantity(){return _quantity;}
  public double getBaseValue(){return _baseValue;}
  public Date getPaymentDate(){return _paymentDate;}
  public Product getProduct(){return _product;}

  public String toString(){
    return getId() +"|"+ getQuantity() + "|" + getBaseValue() + "|" + getPaymentDate();
    }

}
