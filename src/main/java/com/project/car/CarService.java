package com.project.car;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarService {

    private final CarDAO carArrayDataAccessService;

    public CarService(CarDAO carArrayDataAccessService) {
        this.carArrayDataAccessService = carArrayDataAccessService;
    }

    public List<Car> getAllCars() {
        return carArrayDataAccessService.getAllCars();
    }

    public List<Car> getAllAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : getAllCars()) {
            if (car.isAvailable())
                availableCars.add(car);
        }
        return availableCars;
    }

    public List<Car> getAllElectricAvailableCars() {
       List<Car> availableElectricCars = new ArrayList<>();
        for (Car car : getAllAvailableCars()) {
            if (car.isElectric())
                availableElectricCars.add(car);
        }
        return availableElectricCars;
    }

    public Car findCarById(UUID uuid) {
        for (Car car : getAllCars()) {
            if (car.getUuid().equals(uuid)) {
                return car;
            }
        }
        return null;
    }
}
