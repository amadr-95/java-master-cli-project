package com.project.booking;

import java.util.List;

public interface BookingDAO {

    List<Booking> getAllBookings();

    void saveBooking(Booking booking);
}
