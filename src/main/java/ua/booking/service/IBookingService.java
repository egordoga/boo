package ua.booking.service;

import ua.booking.entity.Booking;
import ua.booking.entity.Room;
import ua.booking.entity.User;
import ua.booking.model.BookingForm;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {
    void saveBooking(Booking booking);

    List<Booking> findBookingsByUser(User user);

    String reserveRoom(BookingForm bookingForm);

    List<Booking> findBookings();

    Booking findBooking(Long bid);

    List<Booking> findBookingsFuture();
}
