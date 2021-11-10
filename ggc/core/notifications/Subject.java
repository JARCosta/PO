package ggc.core.notifications;

import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;

abstract class Subject implements Serializable {
	protected List<Observer> _observers = new ArrayList<Observer>();

	abstract void update();
	
	public void addObserver(Observer obs) {
		_observers.add(obs);
	}
	public void removeObserver(Observer obs) {
		_observers.remove(obs);
	}
}
