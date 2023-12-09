package edu.umb.cs681.hw14;



public class DJIAQuoteObservable extends Observable<Double>{
	private double quote;
	public void changeQuote(double q) {
		quote = q; 
		notifyObservers(Double.valueOf(this.quote));
	}
}
