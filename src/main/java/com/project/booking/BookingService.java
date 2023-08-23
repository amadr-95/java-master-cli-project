package com.project.booking;

import com.project.car.Car;
import com.project.car.CarService;
import com.project.user.User;
import com.project.user.UserService;

import java.util.UUID;

public class BookingService {

    private final BookingDAO bookingDAO;
    private final CarService carService;
    private final UserService userService;

    public BookingService() {
        this.bookingDAO = new BookingDAO();
        this.carService = new CarService();
        this.userService = new UserService();
    }

    public Booking[] getAllBookings() {
        return bookingDAO.getAllBookings();
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
        return bookingDAO.saveBooking(new Booking(user, car));
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
