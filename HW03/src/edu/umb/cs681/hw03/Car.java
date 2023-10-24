package edu.umb.cs681.hw03;

import java.util.*;
import java.util.stream.Collectors;


public class Car {
    private String make, model;
    private int mileage, year;
    private float price;
    private int dominationCount;


    public Car(String make, String model, int mileage, int year, float price) {
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public String getModel(){
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public int getDominationCount() {
        return dominationCount;
    }

    public void setDominationCount(ArrayList<Car> cars) {
        for (Car car : cars) {
            if(car.getPrice() <= this.getPrice() && car.getMileage() <= this.getMileage() && car.getYear() >= this.getYear() ){
                if(!(car.getPrice() == this.getPrice() && car.getMileage() == this.getMileage() && car.getYear() == this.getYear())){
                    this.dominationCount ++;
                }
            }
        }
    }


    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Innova", 15, 1995, 25000);
        Car car2 = new Car("Honda", "Civic", 20, 2000, 16000);
        Car car3 = new Car("BMW", "X3", 18, 2006, 26000);
        Car car4 = new Car("Audi", "A3", 14, 2010, 22000);
        Car car5 = new Car("Mercedes", "GLS", 16, 2023, 36000);
        Car car6 = new Car("Ford", "F-150", 10, 2010, 27000);

        List<Car> carList = new ArrayList<>(List.of(car1, car2, car3, car4, car5, car6));

        double averagePrice = carList.stream()
                .map(Car::getPrice)
                .reduce(
                        new CarPriceResultHolder(),
                        (result, price) -> {
                            int numCarsExamined = result.getNumCarExamined();
                            double total = result.getAverage() * numCarsExamined + price;
                            result.setAverage(total / (++numCarsExamined));
                            result.setNumCarsExamined(numCarsExamined);
                            return result;
                        },
                        (finalResult, intermediateResult) -> {
                            int totalCarExamined = finalResult.getNumCarExamined() + intermediateResult.getNumCarExamined();
                            double totalAverage = (finalResult.getAverage() * finalResult.getNumCarExamined()
                                    + intermediateResult.getAverage() * intermediateResult.getNumCarExamined()) / totalCarExamined;
                            return new CarPriceResultHolder(totalCarExamined, totalAverage);
                        }
                ).getAverage();

        System.out.println("Average Price of the Cars from the given list of cars is :" +averagePrice);
    }}

