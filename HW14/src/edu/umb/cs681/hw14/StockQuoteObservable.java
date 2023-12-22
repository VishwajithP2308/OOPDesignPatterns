package edu.umb.cs681.hw14;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable<StockEvent>
{
    HashMap<String,Double> map = new HashMap<>();
    private ReentrantLock reentrantLock = new ReentrantLock();
    
    public void changeQuote(String t,double q)
    {
    	map.put(t, q);
    	notifyObservers(new StockEvent(t, q));
    }
    public HashMap<String, Double> getMap(){
        reentrantLock.lock();
        try {
            return map;
        }finally {
            reentrantLock.unlock();
        }
    }

}
