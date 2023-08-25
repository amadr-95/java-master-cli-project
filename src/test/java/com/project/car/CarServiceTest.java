package com.project.car;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CarServiceTest {

    private static final CarService carService = new CarService(new CarArrayDataAccessService());

    @BeforeEach
    void setupBeforeEach() {
        carService.getAllCars().stream()
                .forEach(car -> car.setAvailable(true));
    }

    @Test
    public void shouldGetAllAvailableCarsAtBeginning() {
        List<CarBrand> brandList = carService.getAllAvailableCars().stream()
                .map(Car::getBrand)
                .toList();
        List<CarBrand> expected = List.of(
                CarBrand.TESLA,
                CarBrand.AUDI,
                CarBrand.MERCEDES
        );
        Assertions.assertEquals(brandList, expected);
        Assertions.assertEquals(brandList.size(), expected.size());
    }

    private void setOneCarBooked() {
        carService.getAllCars().get(1).setAvailable(false);
    }

    @Test
    public void shouldGetTwoAvailableCarsAfterAudiIsBooked() {
        setOneCarBooked();
        List<CarBrand> brandList = carService.getAllAvailableCars().stream()
                .map(Car::getBrand)
                .toList();
        List<CarBrand> expected = List.of(
                CarBrand.TESLA,
                CarBrand.AUDI,
                CarBrand.MERCEDES
        );
        Assertions.assertNotEquals(brandList, expected);
        Assertions.assertEquals(brandList.size(), expected.size() - 1);
        Assertions.assertTrue(brandList.contains(CarBrand.TESLA));
        Assertions.assertFalse(brandList.contains(CarBrand.AUDI));
        Assertions.assertTrue(brandList.contains(CarBrand.MERCEDES));
    }

    @Test
    public void shouldGetAllAvailableElectricCarsAtBeginning() {
        List<CarBrand> brandList = carService.getAllElectricAvailableCars().stream()
                .map(Car::getBrand)
                .toList();
        List<CarBrand> expected = List.of(
                CarBrand.TESLA
        );
        Assertions.assertEquals(brandList, expected);
        Assertions.assertEquals(brandList.size(), expected.size());
    }

    private void setOneElectricCarBooked() {
        carService.getAllCars().get(0).setAvailable(false);
    }

    @Test
    public void shouldGetZeroAvailableElectricCarsAfterTeslaIsBooked() {
        setOneElectricCarBooked();
        List<CarBrand> brandList = carService.getAllElectricAvailableCars().stream()
                .map(Car::getBrand)
                .toList();
        List<CarBrand> expected = List.of(
                CarBrand.TESLA
        );
        Assertions.assertNotEquals(brandList, expected);
        Assertions.assertTrue(brandList.isEmpty());
        Assertions.assertFalse(brandList.contains(CarBrand.TESLA));
    }
}
