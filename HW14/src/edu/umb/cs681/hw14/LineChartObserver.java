package edu.umb.cs681.hw14;

public class LineChartObserver implements Observer<StockEvent>{

	@Override
	public void update(Observable<StockEvent> sender, StockEvent event) {
		String ticker = event.ticker();
        String quote = String.valueOf(event.quote());
        System.out.println("Line Chart Observer...\n");
        System.out.println("Ticker = " + ticker + "\nQuote = " + quote + "\n");
        System.out.println("------------------------------------------------\n");
		
	}
	
	
}
