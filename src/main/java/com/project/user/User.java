package com.project.user;

import com.project.car.Car;

import java.util.Objects;
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

    public User(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.car = null;
    }

    public String getName() {
        return name;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uuid, user.uuid) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }

}
