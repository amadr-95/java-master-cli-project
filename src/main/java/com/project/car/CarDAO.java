package com.project.car;

import java.math.BigDecimal;

public class CarDAO {

    private static final Car[] cars;

    static {
        cars = new Car[]{
                new Car(CarBrand.TESLA, new BigDecimal("69"), true),
                new Car(CarBrand.AUDI, new BigDecimal("45"), false),
                new Car(CarBrand.MERCEDES, new BigDecimal("55"), false)
        };
    }

    public Car[] getAllCars() {
        return cars;
    }
}