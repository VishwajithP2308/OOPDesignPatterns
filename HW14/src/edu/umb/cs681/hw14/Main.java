package edu.umb.cs681.hw14;
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 12; i++){
            StockQuoteRunnable stockQuoteRunnable = new StockQuoteRunnable();
            Thread thread = new Thread(stockQuoteRunnable);
            thread.start();
            try {
                Thread.sleep(200);
            }catch (InterruptedException ex){}
            stockQuoteRunnable.setDone();
            thread.interrupt();

        }
    }
}
