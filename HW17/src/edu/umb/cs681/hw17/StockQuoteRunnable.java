package edu.umb.cs681.hw17;

import java.util.concurrent.atomic.AtomicBoolean;

public class StockQuoteRunnable implements Runnable{
    private AtomicBoolean done = new AtomicBoolean(false);
    public void setDone(){
        done.getAndSet(true);
    }
    @Override
    public void run() {
        while (true){
            if (done.get()){
                System.out.println("Flag based interruption triggered");
                break;
            }
            StockQuoteObservable stockQuoteObservable = new StockQuoteObservable();
            String ticker = "Ticker" + Math.random();
            double newQuote = 20.0 + Math.random() * 10;
            stockQuoteObservable.addObserver(new LineChartObserver());
            stockQuoteObservable.addObserver(new TableObserver());
            stockQuoteObservable.addObserver(new ThreeDObserver());
            stockQuoteObservable.changeQuote(ticker, newQuote);
            System.out.println(stockQuoteObservable.getMap());
            try {
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                System.out.println("Interruption based termination triggered");
            }
        }

    }
}
