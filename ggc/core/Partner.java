package ggc.core;

import java.util.ArrayList;

public class Partner {
    private String _name;
    private String _adress;
    private String _id;
    private String _status;
    private double _points;
    private ArrayList<Batch> _batches;
    private double _valorCompras;
    private double _valorVendas;
    private double _valorVendasPagas;

    public Partner(String id, String name, String adress){
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
        return _id + "|" + _name + "|" + _adress + "|" + _status + "|" + (int)_points + "|" + (int)_valorCompras + "|" + (int)_valorVendas + "|" + (int)_valorVendasPagas;
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
