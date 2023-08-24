package com.project.car;

import java.util.UUID;

public class CarService {

    private final CarDAO carArrayDataAccessService;

    public CarService(CarDAO carArrayDataAccessService) {
        this.carArrayDataAccessService = carArrayDataAccessService;
    }

    public Car[] getAllCars() {
        return carArrayDataAccessService.getAllCars();
    }

    public Car[] getAllAvailableCars() {
        int number = numberOfAvailableCars();
        if (number == 0)
            return new Car[0]; //there are no available cars
        else if (number == getAllCars().length)
            return getAllCars(); //All are available

        Car[] availableCars = new Car[number];
        int index = 0;
        for (Car car : getAllCars()) {
            if (car != null && car.isAvailable())
                availableCars[index++] = car;
        }
        return availableCars;
    }

    private int numberOfAvailableCars() {
        int count = 0;
        for (Car car : getAllCars()) {
            if (car != null && car.isAvailable())
                count++;
        }
        return count;
    }

    public Car[] getAllElectricAvailableCars() {
        int electricCars = getNumberOfElectricAvailableCars();
        if (electricCars == 0)
            return new Car[0];
        else if (electricCars == getAllCars().length) {
            return getAllCars();
        }

        Car[] electricCarsArray = new Car[electricCars];
        int index = 0;
        for (Car car : getAllCars()) {
            if (car != null && car.isAvailable() && car.isElectric())
                electricCarsArray[index++] = car;
        }
        return electricCarsArray;
    }

    private int getNumberOfElectricAvailableCars() {
        int electricCars = 0;
        for (Car car : getAllCars()) {
            if (car != null && car.isAvailable() && car.isElectric())
                electricCars++;
        }
        return electricCars;
    }

    public Car findCarById(UUID uuid) {
        for (Car car : getAllCars()) {
            if (car != null && car.getUuid().equals(uuid)) {
                return car;
            }
        }
        return null;
    }
}
