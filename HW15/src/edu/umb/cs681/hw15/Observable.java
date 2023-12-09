package edu.umb.cs681.hw15;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<T> {
	private ReentrantLock reentrantLock = new ReentrantLock();
	private LinkedList<Observer<T>> observers = new LinkedList<>();
	
	public void addObserver(Observer<T> o) {
		reentrantLock.lock();
		try {
		observers.add(o);
		}finally {
			reentrantLock.unlock();
		}
	}

	public void clearObservers() {
		reentrantLock.lock();
		try {
		observers.clear();
		}finally {
			reentrantLock.unlock();
		}

	}
	public List<Observer<T>> getObservers(){
		reentrantLock.lock();
		try {
		return observers;
		}finally {
			reentrantLock.unlock();
		}
	}
	
	public int countObservers() {
		reentrantLock.lock();
		try {
		return observers.size();
		}finally {
			reentrantLock.unlock();
		}

	}
	public void removeObserver(Observer<T> o) {
		reentrantLock.lock();
		try {
		observers.remove(o);
		}finally {
			reentrantLock.unlock();
		}
	}

	public void notifyObservers(T event) {
		LinkedList<Observer<T>> observersLocal = new LinkedList<>();

		reentrantLock.lock();
		try {
		observersLocal = observers;
		}finally {
			reentrantLock.unlock();
		}
		observers.forEach( (observer)->{observer.update(this, event);} );
	}
	
}
