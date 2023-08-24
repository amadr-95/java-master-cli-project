package com.project;

import com.project.booking.Booking;
import com.project.booking.BookingArrayDataAccessService;
import com.project.booking.BookingDAO;
import com.project.booking.BookingService;
import com.project.car.Car;
import com.project.car.CarArrayDataAccessService;
import com.project.car.CarDAO;
import com.project.car.CarService;
import com.project.user.User;
import com.project.user.UserDAO;
import com.project.user.UserFileDataAccessService;
import com.project.user.UserService;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    //Dependency
    private static final UserDAO userDAO = new UserFileDataAccessService();
    private static final CarDAO carDAO = new CarArrayDataAccessService();
    private static final BookingDAO bookingDAO = new BookingArrayDataAccessService();

    //Injection
    private static final UserService userService = new UserService(userDAO);
    private static final CarService carService = new CarService(carDAO);
    private static final BookingService bookingService = new BookingService(
            bookingDAO,
            carService,
            userService
    );
    private static final Scanner scInt = new Scanner(System.in);
    private static final Scanner scText = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("""
                                        
                    BOOKING CLI SYSTEM ©
                    1️⃣ - Book Car
                    2️⃣ - View All Users Booked Car
                    3️⃣ - View All Bookings
                    4️⃣ - View Available Cars
                    5️⃣ - View Available Electric Cars
                    6️⃣ - View All Users
                    7️⃣ - Exit
                    """);
            option = scInt.nextInt();
            UUID uuidCar;
            UUID uuidUser;
            Booking booking;
            switch (option) {
                case 1 -> {
                    if (carService.getAllAvailableCars().length != 0) {
                        viewAvailableCars();
                        uuidCar = askUuidCar();
                        viewUsers();
                        uuidUser = askUuidUser();
                        booking = bookingService.bookACar(uuidCar, uuidUser);
                        System.out.println("🎉 Succesfully booked car " + booking.getCar() + " by user " + booking.getUser() + "\n" +
                                "Reference: " + booking.getUuid());
                    } else
                        System.out.println("There are no available cars for booking 🙁");
                }
                case 2 -> {
                    viewUsers();
                    uuidUser = askUuidUser();
                    booking = bookingService.getCarBookedByUser(uuidUser);
                    if (booking != null)
                        System.out.println("😀 " + booking.getUser() +
                                " has this car booked:\n" + isElectricEmoji(booking.getCar()) +
                                booking.getCar() + "\nReference: " + booking.getUuid());
                    else
                        System.out.println("User" + userService.findUserById(uuidUser) +
                                " does not have any car booked yet 🙁");
                }
                case 3 -> viewAllBookings();
                case 4 -> viewAvailableCars();
                case 5 -> viewAvailableElectricCars();
                case 6 -> viewUsers();
                case 7 -> System.out.println("Bye! 👋");
                default -> System.out.println("Invalid option ❌");
            }
        } while (option != 7);
    }

    private static UUID askUuidUser() {
        System.out.println("\n⏩ Select uuid user number:");
        return UUID.fromString(scText.nextLine());
    }

    private static UUID askUuidCar() {
        System.out.println("\n⏩ Select uuid car number:");
        return UUID.fromString(scText.nextLine());
    }

    private static void viewAvailableElectricCars() {
        if (carService.getAllElectricAvailableCars().length != 0) {
            for (Car car : carService.getAllElectricAvailableCars()) {
                System.out.println("🔅 " + car);
            }
        } else
            System.out.println("There are no available electric cars 🙁");
    }

    private static void viewAllBookings() {
        if (bookingService.getAllBookings().length != 0) {
            for (Booking b : bookingService.getAllBookings()) {
                System.out.println("🔑 Booking: " + b);
            }
        } else
            System.out.println("There are no bookings yet 😐");
    }

    private static void viewUsers() {
        if (userService.getAllUsers().length != 0) {
            for (User user : userService.getAllUsers()) {
                System.out.println("😀 " + user);
            }
        } else
            System.out.println("There are no users 😐");
    }

    private static void viewAvailableCars() {
        if (carService.getAllAvailableCars().length != 0) {
            for (Car car : carService.getAllAvailableCars()) {
                String isElectric = isElectricEmoji(car);
                System.out.println(isElectric + car);
            }
        } else
            System.out.println("There are no available cars 🙁");

    }

    private static String isElectricEmoji(Car car) {
        return car.isElectric() ? "🔅 " : "🚘 ";
    }
}
