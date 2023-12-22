package edu.umb.cs681.hw17;

public class TableObserver implements Observer<StockEvent> {

	@Override
	public void update(Observable<StockEvent> sender, StockEvent event) {
		String ticker = event.ticker();
        String quote = String.valueOf(event.quote());
        System.out.println("Table Observer...\n");
        System.out.println("Ticker = " + ticker + "\nQuote = " + quote + "\n");
        System.out.println("------------------------------------------------\n");
		
	}

}
