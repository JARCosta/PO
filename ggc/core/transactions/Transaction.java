package ggc.core.transactions;

import java.io.Serializable;

import ggc.core.Date;
import ggc.core.products.Product;

public abstract class Transaction implements Serializable{
  private int _id;
  private Date _paymentDate;
  private double _baseValue;
  private int _quantity;
  private Product _product;
  //private static int _currentId;

  Transaction(Product product,int quantity, int currentId){
    _quantity = quantity;
    _product = product;
    _paymentDate = null;
    _id = currentId;
  }

  public void setBaseValue(double baseValue){_baseValue=baseValue;}
  public int getId(){return _id;}
  public int getQuantity(){return _quantity;}
  public double getBaseValue(){return _baseValue;}
  public Date getPaymentDate(){return _paymentDate;}
  public Product getProduct(){return _product;}
  public void setPaymentDate(Date date){_paymentDate = date;}
  public boolean isPaid(){return false;}

  public String toString(){
    return getId() +"|"+ getQuantity() + "|" + getBaseValue() + "|" + getPaymentDate();
  }
}
