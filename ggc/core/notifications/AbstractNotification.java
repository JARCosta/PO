package ggc.core.notifications;

import java.io.Serializable;

import ggc.core.products.Product;

abstract class AbstractNotification extends Subject {
	private String _type;
	private Product _product;
    private double _price;

	protected AbstractNotification(String type, Product product, double price){
        _type = type;
        _product = product;
        _price = price;
	}

	String getMsg() {
		return _type + "|" + _product.getId() + "|" + (int)_price ;
	}

	@Override
	void update() {
		for(Observer obs : _observers)
			obs.notify(getMsg());
	}
}
