package ua.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.booking.entity.Booking;
import ua.booking.entity.Option;
import ua.booking.entity.Room;
import ua.booking.entity.User;
import ua.booking.model.BookingForm;
import ua.booking.model.UUserTest;
import ua.booking.service.BookingService;
import ua.booking.service.RoomService;
import ua.booking.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RestController/*("/user")*/
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping("/booking")
    public String doBooking(@RequestBody BookingForm bookingForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yy");
        LocalDate startDate = LocalDate.parse(bookingForm.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(bookingForm.getEndDate(), formatter);
        if (startDate.isBefore(LocalDate.now()) || startDate.compareTo(endDate) > 0) {
            return "Проверьте даты";
        }

        List<Room> roomsFree = roomService.findRoomsFree(startDate, endDate);
       /* Room room;
        for (String roomStr : bookingForm.getRooms()) {*/
        String roomStr = bookingForm.getRoom();
        Room room = roomService.findRoomByNumber(Integer.valueOf(roomStr));
        if (!roomsFree.contains(room)) {
            return "Room " + roomStr + " is busy at this dates";
        }
        //}

        return bookingService.reserveRoom(bookingForm);
    }

    @GetMapping("user_booking")
    public List<Booking> sendBookingByUser(@RequestParam("user") String username) {
        User user = userService.findUserByName(username);
        return bookingService.findBookingsByUser(user);
    }

    @GetMapping("/all_bookings")
    public List<Booking> sendBookings() {
        return bookingService.findBookings();

        //Возможно в задании имелось ввиду просмотр всех резервирований на будущий период. Тогда
        //return bookingService.findBookingsFuture();
    }

    @GetMapping("/cost")
    public String sendCost(@RequestParam("id") String strId) {
        Booking booking = bookingService.findBooking(Long.parseLong(strId));
        BigDecimal additionalCost = booking.getOptions().stream().map(Option::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        return booking.getRoom().getPrice().add(additionalCost).toString();
    }
}
