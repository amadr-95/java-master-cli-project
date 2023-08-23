package com.project.car;

import java.util.UUID;

public class CarService {

    private final CarDAO carDAO;

    public CarService() {
        this.carDAO = new CarDAO();
    }

    public Car[] getAllCars() {
        return carDAO.getAllCars();
    }

    public Car[] getAllAvailableCars() {
        int number = numberOfAvailableCars();
        if (number == 0)
            return new Car[0];
        else if (number == getAllCars().length)
            return getAllCars();

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

    public void addNewCar(Car car) {
        if (car.getPricePerDay().doubleValue() <= 0)
            throw new IllegalArgumentException("Car price is not valid");
        carDAO.saveCar(car);
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
