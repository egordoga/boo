package ua.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.booking.entity.Booking;
import ua.booking.entity.Option;
import ua.booking.model.CostForm;
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
    }

    @GetMapping("/cost/{id}")
    public CostForm sendCost(@PathVariable("id") Booking booking) {
        BigDecimal mainCost = booking.getRoom().getPrice();
        BigDecimal additionalCost = booking.getOptions().stream().map(Option::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCost = mainCost.add(additionalCost);
        return new CostForm(mainCost, additionalCost, totalCost);
    }
}
