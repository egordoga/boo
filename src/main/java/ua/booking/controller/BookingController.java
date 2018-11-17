package ua.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.booking.entity.Booking;
import ua.booking.entity.Option;
import ua.booking.service.BookingService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/all_bookings")
    public List<Booking> sendBookings() {
        return bookingService.findBookings();

        //Возможно в задании имелось ввиду просмотр всех резервирований на будущий период. Тогда
        //return bookingService.findBookingsFuture();
    }

    @GetMapping("/cost")
    public String sendCost(@RequestParam("id") Long id) {
        Booking booking = bookingService.findBooking(id);
        BigDecimal additionalCost = booking.getOptions().stream().map(Option::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        return String.format("Full cost of booking %d is %s", booking.getId(), booking.getRoom().getPrice().add(additionalCost).toString());
    }
}
