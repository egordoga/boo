package ua.booking.service;

import ua.booking.entity.Booking;
import ua.booking.entity.User;
import ua.booking.model.BookingForm;
import ua.booking.model.ReservedForm;

import java.util.List;

public interface IBookingService {

    List<Booking> findBookingsByUser(User user);

    ReservedForm reserveRoom(BookingForm bookingForm);

    List<Booking> findBookings();

    Booking findBooking(Long bid);

    List<Booking> findBookingsFuture();
}
