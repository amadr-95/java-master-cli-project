package com.project.booking;

import java.util.ArrayList;
import java.util.List;

public class BookingArrayDataAccessService implements BookingDAO {
    private static List<Booking> bookings;

    public BookingArrayDataAccessService() {
        bookings = new ArrayList<>();
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookings;
    }

    @Override
    public void saveBooking(Booking booking) {
        bookings.add(booking);
    }
}