package ggc.core;

import java.io.Serializable;

import ggc.core.exception.InvalidDateException;

public class Date implements Serializable{
  private static Date _now = new Date();
  private int _days;

  public Date(){
    _days = 0;
  }

  public void advanceDate(int days) throws InvalidDateException{
    if(days < 1){
      throw new InvalidDateException(days);
    }
    _days += days;
  }

  public int currentDate(){
    return _days;
  }

  public int difference(Date other){
    return _days - other.currentDate();
  }

  public static Date now(){
    return _now;
  }
}
