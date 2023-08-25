package com.project.car;

import java.math.BigDecimal;
import java.util.UUID;

public class Car {
    private final UUID uuid;
    private final CarBrand brand;
    private final BigDecimal pricePerDay;
    private final boolean isElectric;
    private boolean isAvailable;

    public Car(CarBrand brand, BigDecimal pricePerDay, boolean isElectric) {
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.isElectric = isElectric;
        this.uuid = UUID.randomUUID();
        this.isAvailable = true;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return "Car{" +
                "uuid=" + uuid +
                ", brand='" + brand + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", isElectric=" + isElectric +
                '}';
    }
}
