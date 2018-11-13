package ua.booking.service;

import ua.booking.entity.Booking;
import ua.booking.entity.Room;
import ua.booking.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {
    void saveBooking(Booking booking);

    List<Booking> findBookingsByUser(User user);

    void reserveRoom(Room room, User user, LocalDate start, LocalDate end);
}
