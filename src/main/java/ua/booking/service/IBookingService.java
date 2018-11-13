package ua.booking.service;

import ua.booking.entity.Booking;
import ua.booking.entity.User;

import java.util.List;

public interface IBookingService {
    void saveBooking(Booking booking);

    List<Booking> findBookingsByUser(User user);
}
