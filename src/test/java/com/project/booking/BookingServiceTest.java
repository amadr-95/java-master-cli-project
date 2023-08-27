package com.project.booking;

import com.project.car.CarArrayDataAccessService;
import com.project.car.CarService;
import com.project.user.UserFileDataAccessService;
import com.project.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingServiceTest {

    private final CarService carService = new CarService(new CarArrayDataAccessService());
    private final UserService userService = new UserService(new UserFileDataAccessService());
    private final BookingService bookingService = new BookingService(
            new BookingArrayDataAccessService(),
            carService,
            userService
    );

    @BeforeEach
    public void setupBeforeEach() {
        bookingService.getAllBookings().clear();
    }

    @Test
    public void shouldGetZeroBookingsAtBegining() {
        Assertions.assertTrue(bookingService.getAllBookings().isEmpty());
    }

    @Test
    public void shouldNotGetABookingByUser() {
        Assertions.assertNull(bookingService.getCarBookedByUser(userService.getAllUsers().get(0).getUuid()));
    }

    private Booking makeABooking() {
        return bookingService.bookACar(carService.getAllCars().get(0).getUuid(),
                userService.getAllUsers().get(0).getUuid());
    }

    @Test
    public void shouldGetOneBookingAfter() {
        Booking booking = makeABooking();
        Assertions.assertFalse(bookingService.getAllBookings().isEmpty());
        Assertions.assertEquals(bookingService.getAllBookings().size(), 1);
        Assertions.assertEquals(bookingService.getAllBookings().get(0), booking);
    }

    @Test
    public void shouldGetABookingByUser() {
        Booking b = makeABooking();
        Booking booking = bookingService.getCarBookedByUser(userService.getAllUsers().get(0).getUuid());
        Assertions.assertEquals(booking, b);
    }
}
