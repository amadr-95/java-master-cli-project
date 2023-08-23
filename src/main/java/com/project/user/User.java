package com.project.user;

import com.project.car.Car;

import java.util.UUID;

public class User {
    private static final int NUM_CARS_BOOKED = 3;
    private final UUID uuid;
    private final String name;
    private final Car[] carsBooked;
    private int nextAvailableSlot;

    public User(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.carsBooked = new Car[NUM_CARS_BOOKED];
        this.nextAvailableSlot = 0;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Car[] getCarsBooked() {
        return carsBooked;
    }

    public void setCarsBooked(Car car) {
        if (nextAvailableSlot >= NUM_CARS_BOOKED)
            throw new IllegalStateException("User " + name + " has reached the limit");
        this.carsBooked[nextAvailableSlot++] = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }

}
