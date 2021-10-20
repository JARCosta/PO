package ggc.core;

public class Date {
    private int _days;

    public Date(int date){
        _days = date;
    }

    public void advanceDate(int days){
        if(0 < days)
        _days += days;
    }

    public int currentDate(){
        return _days;
    }

    public int difference(Date other){
        return _days - other.currentDate();
    }
/*
    public static Date now(){
    }
*/

}
