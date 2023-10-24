package edu.umb.cs681.hw04;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private List<Double> carList;
//	private String make, model;
//	private double mileage, year;
//	private double price;

    public Car(double mileage, double year, double price) {
        this.carList = new ArrayList<>();
        this.carList.add(mileage);
        this.carList.add(year);
        this.carList.add( price);
    }

    public List<Double> getValues() {
        return carList;
    }

    public static List<Car> normalize(List<Car> cars) {
        List<Car> normalizedCars = new ArrayList<>();

        double minMileage = Double.POSITIVE_INFINITY;
        double maxMileage = Double.NEGATIVE_INFINITY;

        double minYear = Double.POSITIVE_INFINITY;
        double maxYear = Double.NEGATIVE_INFINITY;

        double minPrice = Double.POSITIVE_INFINITY;
        double maxPrice = Double.NEGATIVE_INFINITY;

        for (Car car : cars) {
            double value1 = car.getValues().get(0);
            double value2 = car.getValues().get(1);
            double value3 = car.getValues().get(2);

            if (value1 < minMileage) {
                minMileage = value1;
            }
            if (value1 > maxMileage) {
                maxMileage = value1;
            }
            if (value2 < minYear) {
                minYear = value2;
            }
            if (value2 > maxYear) {
                maxYear = value2;
            }
            if (value3 < minPrice) {
                minPrice = value3;
            }
            if (value3 > maxPrice) {
                maxPrice = value3;
            }
        }

        for (Car car : cars) {
            double value1 = (car.getValues().get(0) - minMileage) / (maxMileage - minMileage);
            double value2 = (car.getValues().get(1) - minYear) / (maxYear - minYear);
            double value3 = (car.getValues().get(2) - minPrice) / (maxPrice - minPrice);

            normalizedCars.add(new Car(value1, value2, value3));
        }

        return normalizedCars;
    }
}
