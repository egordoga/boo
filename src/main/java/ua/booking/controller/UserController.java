package ua.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.booking.entity.Booking;
import ua.booking.entity.Room;
import ua.booking.entity.User;
import ua.booking.model.BookingForm;
import ua.booking.service.BookingService;
import ua.booking.service.RoomService;
import ua.booking.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController("/user")
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

        List<Room> roomsFree = roomService.findRoomsFree(startDate, endDate);
        Room room;
        for (String roomStr : bookingForm.getRooms()) {
            room = roomService.findRoomByNumber(Integer.valueOf(roomStr));
            if (!roomsFree.contains(room)) {
                return "Room " + roomStr + " is busy at this dates";
            }
        }

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

        //Возможно в задании имелось ввиду просмотр всех будущих резервирований. Тогда
        //return bookingService.findBookingsFuture();
    }

    @GetMapping("/cost")
    public String sendCost(@RequestParam("id") String strId) {
        Booking booking = bookingService.findBooking(Long.parseLong(strId));
        BigDecimal mainCost =
    }
}
