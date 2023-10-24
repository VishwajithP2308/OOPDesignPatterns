
package edu.umb.cs681.hw03;

public class CarPriceResultHolder {
    private int numCarExamined;
    private double average;
    public CarPriceResultHolder(){
        numCarExamined = 0;
        average = 0.0;
    }

    public CarPriceResultHolder(int numCarExamined, double sum, double average) {
        this.numCarExamined = numCarExamined;
        this.average = average;
    }

    public CarPriceResultHolder(int totalNumCarsExamined, double totalAverage) {
    }

    public int getNumCarExamined() {
        return numCarExamined;
    }

    public void setNumCarExamined(int numCarExamined) {
        this.numCarExamined = numCarExamined;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setNumCarsExamined(int numCarsExamined) {
        this.numCarExamined = numCarsExamined;
    }
}
