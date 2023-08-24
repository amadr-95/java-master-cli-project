package com.project.car;

import java.math.BigDecimal;
import java.util.List;

public class CarArrayDataAccessService implements CarDAO {

    private static final List<Car> cars;

    static {
        cars = List.of(
                new Car(CarBrand.TESLA, new BigDecimal("69"), true),
                new Car(CarBrand.AUDI, new BigDecimal("45"), false),
                new Car(CarBrand.MERCEDES, new BigDecimal("55"), false)
        );
    }

    @Override
    public List<Car> getAllCars() {
        return cars;
    }
}