package com.project;

import com.project.booking.Booking;
import com.project.booking.BookingService;
import com.project.car.Car;
import com.project.car.CarBrand;
import com.project.car.CarService;
import com.project.user.User;
import com.project.user.UserService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final UserService userService = new UserService();
    private static final CarService carService = new CarService();
    private static final BookingService bookingService = new BookingService();
    private static final Scanner scInt = new Scanner(System.in);
    private static final Scanner scText = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("""
                                        
                    BOOKING CLI SYSTEM ©
                    1️⃣ - Book Car
                    2️⃣ - View Cars Booked By User
                    3️⃣ - View All Bookings
                    4️⃣ - View Available Cars
                    5️⃣ - View Available Electric Cars
                    6️⃣ - Add New Car
                    7️⃣ - View All Users
                    8️⃣ - Add New User
                    9️⃣ - Exit
                    """);
            option = scInt.nextInt();
            UUID uuidCar;
            UUID uuidUser;
            Booking booking;
            Booking[] bookings;
            Car car;
            User user;
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
                    bookings = bookingService.getCarsBookedByUser(uuidUser);
                    if (bookings.length != 0) {
                        System.out.println("😀 " + userService.findUserById(uuidUser) +
                                " has these cars booked:");
                        for (Booking b : bookings) {
                            System.out.println(isElectricEmoji(b.getCar()) + b.getCar() +
                                    "\nReference: " + b.getUuid());
                        }
                    } else
                        System.out.println("User " + userService.findUserById(uuidUser) +
                                " does not have any car booked yet 🙁");
                }
                case 3 -> viewAllBookings();
                case 4 -> viewAvailableCars();
                case 5 -> viewAvailableElectricCars();
                case 6 -> {
                    car = new Car(askBrand(), askPrice(), isElectric());
                    carService.addNewCar(car);
                    System.out.println(isElectricEmoji(car) + car + " succesfully added to database");
                }
                case 7 -> viewUsers();
                case 8 -> {
                    user = new User(askName());
                    userService.addNewUser(user);
                    System.out.println("😀 User " + user + " succesfully added to database");
                }
                case 9 -> System.out.println("Bye! 👋");
                default -> System.out.println("Invalid option ❌");
            }
        } while (option != 9);
    }

    private static String askName() {
        System.out.println("⏩ Name:");
        return scText.nextLine();
    }

    private static boolean isElectric() {
        System.out.println("🔅 Electric? (true/false):");
        return scInt.nextBoolean();
    }

    private static BigDecimal askPrice() {
        System.out.println("💲 Price per day:");
        return scInt.nextBigDecimal();
    }

    private static CarBrand askBrand() {
        System.out.println("⏩ Pick a brand:");
        Arrays.stream(CarBrand.values()).forEach(System.out::println);
        return CarBrand.valueOf(scText.nextLine());
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
