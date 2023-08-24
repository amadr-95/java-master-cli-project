package com.project.car;

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
        return getAllCars().stream()
                .filter(Car::isAvailable)
                .toList();
    }

    public List<Car> getAllElectricAvailableCars() {
       return getAllAvailableCars().stream()
               .filter(Car::isElectric)
               .toList();
    }

    public Car findCarById(UUID uuid) {
        return getAllCars().stream()
                .filter(car -> car.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
    }
}
