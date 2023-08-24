package com.project.booking;

import com.project.car.Car;
import com.project.car.CarService;
import com.project.user.User;
import com.project.user.UserService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class BookingService {

    private final BookingDAO bookingArrayDataAccessService;
    private final CarService carService;
    private final UserService userService;

    public BookingService(BookingDAO bookingArrayDataAccessService, CarService carService, UserService userService) {
        this.bookingArrayDataAccessService = bookingArrayDataAccessService;
        this.carService = carService;
        this.userService = userService;
    }

    public Booking[] getAllBookings() {
        return bookingArrayDataAccessService.getAllBookings();
    }

    public Booking bookACar(UUID uuidCar, UUID uuidUser) {
        Car car = carService.findCarById(uuidCar);
        User user = userService.findUserById(uuidUser);
        if (car == null)
            throw new IllegalArgumentException("Car does not exist");
        if (user == null)
            throw new IllegalArgumentException("User does not exist");
        if (user.getCar() != null)
            throw new IllegalArgumentException("User has already booked a car");

        car.setAvailable(false);
        user.setCar(car);
        Booking booking = new Booking(user, car);
        saveBookingToAFile(booking);
        return bookingArrayDataAccessService.saveBooking(booking);
    }

    private void saveBookingToAFile(Booking booking) {
        String PATH = "src/main/resources/bookings.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(PATH, true))) {
            writer.println(booking.getUser());
            writer.println(booking.getCar());
            writer.println("Ref: " + booking.getUuid());
            writer.println("Date: " + booking.getDate());
            writer.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Booking getCarBookedByUser(UUID uuidUser) {
        for (Booking booking : getAllBookings()) {
            if (booking.getUser().getUuid().equals(uuidUser)) {
                return booking;
            }
        }
        return null;
    }
}
