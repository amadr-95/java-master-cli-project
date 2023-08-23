package com.project.booking;

import java.util.Arrays;

public class BookingDAO {
    private static final int CAPACITY = 5;
    private static Booking[] bookings;
    private static int nextAvailableSlot;

    static {
        bookings = new Booking[CAPACITY];
        nextAvailableSlot = 0;
    }

    public Booking[] getAllBookings() {
        int numberOfBookings = getNumberOfBookings();
        if (numberOfBookings == 0)
            return new Booking[0];
        else if (numberOfBookings == bookings.length)
            return bookings;

        Booking[] getBookings = new Booking[numberOfBookings];
        int index = 0;
        for (Booking booking : bookings) {
            if (booking != null)
                getBookings[index++] = booking;
        }
        return getBookings;
    }

    private int getNumberOfBookings() {
        int count = 0;
        for (Booking booking : bookings) {
            if (booking != null)
                count++;
        }
        return count;
    }

    public Booking saveBooking(Booking booking) {
        if (nextAvailableSlot >= CAPACITY)
            bookings = growBookings();
        return bookings[nextAvailableSlot++] = booking;
    }

    private Booking[] growBookings() {
        return Arrays.copyOf(bookings, bookings.length + 1);
    }
}