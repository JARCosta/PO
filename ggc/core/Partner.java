package ggc.core;

import java.util.ArrayList;

public class Partner {
    private String _name;
    private String _adress;
    private String _id;
    private String _status;
    private double _points;

    public Partner(String name, String adress, String id){
        _name = name;
        _adress = adress;
        _id = id;
        _status = "";
        _points = 0;
    }

    public String getId(){
        return _id;
    }

    @Override
    public String toString() {
        return _id + "|" + _name + "|" + _adress + "|" + _points + "|";
    }

    public double getPoints(){
        return _points;
    }

    public void addPoints(double adding){
        _points += adding;
    }

    public void updateStatus(){
        if(_points>2000)
            if(_points>25000)
                _status = "Elite";
            else
            _status = "Selection";
        else
        _status = "Normal";
    }


}
