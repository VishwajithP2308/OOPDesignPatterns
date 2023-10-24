package edu.umb.cs681.hw01;

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

    public void setDominationCount(List<Car> cars) {
        for (Car car : cars) {
            if(car.getPrice() <= this.getPrice() && car.getMileage() <= this.getMileage() && car.getYear() <= this.getYear() ){
                if(!(car.getPrice() == this.getPrice() && car.getMileage() == this.getMileage() && car.getYear() == this.getYear())){
                    this.dominationCount ++;
                }
            }
        }
    }


    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Innova", 10, 1995, 15000);
        Car car2 = new Car("Honda", "Civic", 14, 2001, 21000);
        Car car3 = new Car("BMW", "X3", 20, 2011, 28000);
        Car car4 = new Car("Audi", "A3", 13, 2000, 20000);
        Car car5 = new Car("Mercedes", "GLS", 15, 2005, 25000);
        Car car6 = new Car("Ford", "F-150", 22, 2023, 35000);
        Car car7 = new Car("Toyota", "Tacuma", 16, 2010, 26000);
        Car car8 = new Car("Lexus", "L5", 21, 2020, 33000);
        //   Car car9 = new Car("Mercedes","F1",17,2020,40000);


        List<Car> carList = new ArrayList<>(List.of(car1, car2, car3, car4, car5, car6, car7, car8));

        System.out.println("$$$$$$$$$$$$$$$$$$$-FILTERING WITH PRICE-$$$$$$$$$$$$$$$$$$$");

        Map<Boolean, List<Car>> PriceFactor = carList.
                stream().collect(Collectors.partitioningBy((Car car) -> car.getPrice() < 26000));

        List<Car> LowPricedCars = PriceFactor.get(true);
        List<Double> LowPricedCarsPrices = LowPricedCars.
                stream().map(Car::getPrice).collect(Collectors.toList());

        //List<Integer> CarYearLowPricedCars = LowPricedCars.stream().map(Car::getYear).toList();

        System.out.println("^^^^^^^^^^Low Priced Cars^^^^^^^^^");

        System.out.println("Prices of the cars in the low priced :" + LowPricedCarsPrices);
        //System.out.println("Year : " + CarYearLowPricedCars);

        OptionalDouble highestPricedLP = LowPricedCars.stream()
                .mapToDouble(Car::getPrice)
                .max();
        System.out.println("Highest Price of the cars in low priced cars " + highestPricedLP.getAsDouble());

        OptionalDouble lowestPricedLP = LowPricedCars.stream()
                .mapToDouble(Car::getPrice)
                .min();
        System.out.println("Lowest Price of the cars in low priced cars " + lowestPricedLP.getAsDouble());

        OptionalDouble avgPriceLP = LowPricedCars.stream()
                .mapToDouble(Car::getPrice)
                .average();
        System.out.println("Average Price of the cars in low priced cars " + avgPriceLP.getAsDouble());

        System.out.println("Number of cars in low priced cars == " +LowPricedCars.stream().count());

        List<Car> HighPricedCars = PriceFactor.get(false);

        List<Double> HighPricedCarsPrices = HighPricedCars.stream().map(Car::getPrice).toList();

        //List<Integer> CarYearHighPricedCars = HighPricedCars.stream().map(Car::getYear).toList();

        System.out.println("^^^^^^^^^^High Priced Cars^^^^^^^^^");
        System.out.println("Prices :" + HighPricedCarsPrices);
        //System.out.println("Year : " + CarYearLowPricedCars);

        OptionalDouble highestPricedHP = HighPricedCars.stream()
                .mapToDouble(Car::getPrice)
                .max();
        System.out.println("Highest Price of the cars in high priced cars " + highestPricedHP.getAsDouble());


        OptionalDouble lowestPriceHP = HighPricedCars.stream()
                .mapToDouble(Car::getPrice)
                .min();
        System.out.println("Lowest Price of the cars in high priced cars " + lowestPriceHP.getAsDouble());

        OptionalDouble avgPriceHP = HighPricedCars.stream()
                .mapToDouble(Car::getPrice)
                .average();
        System.out.println("Average Price of the cars in high priced cars " + avgPriceHP.getAsDouble());

        System.out.println("Number of cars in high priced cars == " +HighPricedCars.stream().count());


        System.out.println("!!!!!!!!!!!!!!!!!!!!!!-FILTERING WITH MILEAGE-!!!!!!!!!!!!!!!!!!!!!!");

        Map<Boolean, List<Car>> MileageFactor = carList.
                stream().collect(Collectors.partitioningBy((Car car) -> car.getMileage() <= 15));

        List<Car> LowMileageCars = MileageFactor.get(true);

        List<Integer> LowMileageCarsMileage = LowMileageCars.stream()
                .map(Car::getMileage).collect(Collectors.toList());

        //List<Double> CarPriceLowMileageCars = LowMileageCars.stream()
        //.map(Car::getPrice).toList();

        System.out.println("^^^^^^^^^^Low Mileage Cars^^^^^^^^^");

        System.out.println("Mileage of the cars in the low Mileage :" + LowMileageCarsMileage);
        //System.out.println("Price : " + CarPriceLowMileageCars);

        OptionalDouble highPriceLowMileage = LowMileageCars.stream()
                .mapToDouble(Car::getPrice)
                .max();
        System.out.println("Highest Price of the cars in low Mileage cars " + highPriceLowMileage.getAsDouble());


        OptionalDouble lowPriceLowMileage = LowMileageCars.stream()
                .mapToDouble(Car::getPrice)
                .min();
        System.out.println("Lowest Price of the cars in low Mileage cars " + lowPriceLowMileage.getAsDouble());

        OptionalDouble avgYearLowMileage = LowMileageCars.stream()
                .mapToDouble(Car::getPrice)
                .average();
        System.out.println("Average Price of the cars in low mileage cars " + avgYearLowMileage.getAsDouble());

        System.out.println("Number of cars in Low Mileage cars == " +LowMileageCars.stream().count());



        System.out.println("^^^^^^^^^^High Mileage Cars^^^^^^^^^");
        List<Car> HighMileageCars = MileageFactor.get(false);

        List<Integer> HighMileageCarsMileage = HighMileageCars.stream()
                .map(Car::getMileage).collect(Collectors.toList());

        List<Double> CarPriceHighMileageCars = HighMileageCars.stream()
                .map(Car::getPrice).toList();

        System.out.println("Mileage of the cars in the High Mileage :" + HighMileageCarsMileage);
        System.out.println("Price : " + CarPriceHighMileageCars);

        OptionalDouble highPriceHighMileage = HighMileageCars.stream()
                .mapToDouble(Car::getPrice)
                .max();
        System.out.println("Highest Price of the cars in high Mileage cars " + highPriceHighMileage.getAsDouble());


        OptionalDouble lowPriceHighMileage = HighMileageCars.stream()
                .mapToDouble(Car::getPrice)
                .min();
        System.out.println("Lowest Price of the cars in high Mileage cars " + lowPriceHighMileage.getAsDouble());

        OptionalDouble avgYearHighMileage = HighMileageCars.stream()
                .mapToDouble(Car::getPrice)
                .average();
        System.out.println("Average Price of the cars in high mileage cars " + avgYearHighMileage.getAsDouble());
        System.out.println("Number of cars in high mileage cars == " +HighMileageCars.stream().count());


        System.out.println("################-FILTERING WITH YEAR-####################");

        Map<Boolean, List<Car>> YearFactor = carList.
                stream().collect(Collectors.partitioningBy((Car car) -> car.getYear() > 2005));

        List<Car> LatestCars = YearFactor.get(true);

        List<Integer> YearLatestCars = LatestCars.stream()
                .map(Car::getYear).collect(Collectors.toList());

        List<Integer> MileageLatestCars = LatestCars.stream()
                .map(Car::getMileage).toList();

        System.out.println("^^^^^^^^^^Latest Cars^^^^^^^^^");

        System.out.println("Manufacturing Year of the cars in the latest Cars :" + YearLatestCars);
        System.out.println("Mileage : " + MileageLatestCars);

        OptionalDouble highMileageLatestCars = LatestCars.stream()
                .mapToDouble(Car::getMileage)
                .max();
        System.out.println("Highest Mileage of the cars in latest cars " + highMileageLatestCars.getAsDouble());


        OptionalDouble lowMileageLatestCars = LatestCars.stream()
                .mapToDouble(Car::getMileage)
                .min();
        System.out.println("Lowest Mileage of the cars in latest cars " + lowMileageLatestCars.getAsDouble());

        OptionalDouble avgMileageLatestYear = LatestCars.stream()
                .mapToDouble(Car::getMileage)
                .average();
        System.out.println("Average Mileage of the cars in latest cars " + avgMileageLatestYear.getAsDouble());
        System.out.println("Number of cars in latest cars  == " +LatestCars.stream().count());

        List<Car> OldCars = YearFactor.get(false);

        List<Integer> YearOldCars = OldCars.stream()
                .map(Car::getYear).collect(Collectors.toList());

        List<Integer> MileageOldCars = OldCars.stream()
                .map(Car::getMileage).toList();

        System.out.println("^^^^^^^^^^Old Cars^^^^^^^^^");

        System.out.println("Manufacturing Year of the cars in the old Cars :" + YearOldCars);
        System.out.println("Mileage : " + MileageOldCars);

        OptionalDouble highMileageOldCars = OldCars.stream()
                .mapToDouble(Car::getMileage)
                .max();
        System.out.println("Highest Mileage of the cars in old cars " + highMileageOldCars.getAsDouble());


        OptionalDouble lowMileageOldCars = OldCars.stream()
                .mapToDouble(Car::getMileage)
                .min();
        System.out.println("Lowest Mileage of the cars in old cars " + lowMileageOldCars.getAsDouble());

        OptionalDouble avgMileageOldYear = OldCars.stream()
                .mapToDouble(Car::getMileage)
                .average();
        System.out.println("Average Mileage of the cars in old cars " + avgMileageOldYear.getAsDouble());
        System.out.println("Number of cars in old cars  == " +OldCars.stream().count());

        System.out.println("++++++++++++++++++++-FILTERING WITH DOMINATION COUNT-++++++++++++++++++++++");

        for(Car c:carList){
            c.setDominationCount(carList);
        }

        Map<Boolean, List<Car>> DomCountFactor = carList.
                stream().collect(Collectors.partitioningBy((Car car) -> car.getDominationCount() >= 4));

        List<Car> HighDomCars = DomCountFactor.get(true);

        System.out.println("^^^^^^^^^^High Domination Count Cars^^^^^^^^^");
        List<Integer> DomCountHigh = HighDomCars.stream()
                .map(Car::getDominationCount).collect(Collectors.toList());
        System.out.println("Domination Count Of High Dom cars" +DomCountHigh);

        OptionalDouble highPriceHighDom = HighDomCars.stream()
                .mapToDouble(Car::getPrice)
                .max();
        System.out.println("Highest price of the car in high Dom cars " + highPriceHighDom.getAsDouble());

        OptionalDouble lowPriceHighDom = HighDomCars.stream()
                .mapToDouble(Car::getPrice)
                .min();
        System.out.println("Lowest price of the car in high Dom cars " + lowPriceHighDom.getAsDouble());

        OptionalDouble averageMileageHighDom = HighDomCars.stream()
                .mapToDouble(Car::getPrice)
                .average();
        System.out.println("Average price of the car in high Dom cars " + averageMileageHighDom.getAsDouble());
        System.out.println("Number of cars in high dom cars == " +HighDomCars.stream().count());


        System.out.println("^^^^^^^^^^Low Domination Count Cars^^^^^^^^^^");
        List<Car> LowDomCars = DomCountFactor.get(false);
        List<Integer> DomCountLow = LowDomCars.stream()
                .map(Car::getDominationCount).collect(Collectors.toList());
        System.out.println("Domination Count Of Low Dom cars" +DomCountLow);
        OptionalDouble highPriceLowDom = LowDomCars.stream()
                .mapToDouble(Car::getPrice)
                .max();

        System.out.println("Highest price of the car in low Dom cars " + highPriceLowDom.getAsDouble());

        OptionalDouble lowPriceLowDom = LowDomCars.stream()
                .mapToDouble(Car::getPrice)
                .min();

        System.out.println("Lowest price of the car in low Dom cars " + lowPriceLowDom.getAsDouble());

        OptionalDouble averagePriceLowDom = LowDomCars.stream()
                .mapToDouble(Car::getPrice)
                .average();

        System.out.println("Average price of the car in low Dom cars " + averagePriceLowDom.getAsDouble());
        System.out.println("Number of cars in low dom cars == " +LowDomCars.stream().count());
    }

}
