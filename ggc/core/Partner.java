package ggc.core;

import java.util.ArrayList;

public class Partner {
    private String _name;
    private String _adress;
    private String _id;
    private String _status;
    private double _points;
    private ArrayList<Batch> _batches;

    public Partner(String name, String adress, String id){
        _name = name;
        _adress = adress;
        _id = id;
        _status = "NORMAL";
        _points = 0;
    }

    public String getId(){
        return _id;
    }

    @Override
    public String toString() {
        return _id + "|" + _name + "|" + _adress + "|" + _points + "|" + _status + 
            "|valor-compras|valor-vendas-efectuadas|valor-vendas-pagas";
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
                _status = "ELITE";
            else
            _status = "SELECTION";
        else
        _status = "NORMAL";
    }

    public void registerBatch(double price, int stock, Partner partner, Product product){
        Batch batch = new Batch(price, stock, partner, product);
        _batches.add(batch);
    }

    public ArrayList<Batch> getBatches(){
        return _batches;
    }
}
