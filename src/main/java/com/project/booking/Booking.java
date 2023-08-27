package com.project.booking;

import com.project.car.Car;
import com.project.user.User;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Booking {
    private final UUID uuid;
    private final LocalDate date;
    private final User user;
    private final Car car;

    public Booking(User user, Car car) {
        this.uuid = UUID.randomUUID();
        this.date = LocalDate.now();
        this.user = user;
        this.car = car;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(uuid, booking.uuid) && Objects.equals(date, booking.date) && Objects.equals(user, booking.user) && Objects.equals(car, booking.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, date, user, car);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "uuid=" + uuid +
                ", date=" + date +
                ", user=" + user +
                ", car=" + car +
                '}';
    }
}
