package com.project.user;

import com.project.car.Car;

import java.util.UUID;

public class User {
    private final UUID uuid;
    private final String name;
    private Car car;

    public User(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.car = null;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }

}
