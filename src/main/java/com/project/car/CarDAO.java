package com.project.car;

import java.math.BigDecimal;
import java.util.Arrays;

public class CarDAO {

    private static final int CAPACITY = 5;
    private static Car[] cars;
    private static int nextAvailableSlot;

    static {
        cars = new Car[CAPACITY];
        cars[0] = new Car(CarBrand.TESLA, new BigDecimal("69"), true);
        cars[1] = new Car(CarBrand.AUDI, new BigDecimal("45"), false);
        cars[2] = new Car(CarBrand.BMW, new BigDecimal("55"), false);
        nextAvailableSlot = 3;
    }

    public Car[] getAllCars() {
        int number = numberOfCars();
        if (number == 0)
            return new Car[0];
        else if (number == cars.length) {
            return cars;
        }

        Car[] getCars = new Car[number];
        int index = 0;
        for (Car car : cars) {
            if (car != null)
                getCars[index++] = car;
        }
        return getCars;
    }

    private int numberOfCars() {
        int count = 0;
        for (Car car : cars) {
            if (car != null)
                count++;
        }
        return count;
    }

    public void saveCar(Car car) {
        if (nextAvailableSlot >= CAPACITY)
            cars = growCars();
        cars[nextAvailableSlot++] = car;
    }

    private Car[] growCars() {
        return Arrays.copyOf(cars, cars.length + 1);
    }
}