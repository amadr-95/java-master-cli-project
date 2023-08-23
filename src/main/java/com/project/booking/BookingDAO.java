package com.project.booking;

public interface BookingDAO {

    Booking[] getAllBookings();

    Booking saveBooking(Booking booking);
}
