package ua.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.booking.entity.Booking;
import ua.booking.entity.Option;
import ua.booking.entity.Room;
import ua.booking.entity.User;
import ua.booking.model.BookingForm;
import ua.booking.model.UserForm;
import ua.booking.service.BookingService;
import ua.booking.service.RoomService;
import ua.booking.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    public void addUser(@RequestBody UserForm userForm) {
        User user = new User(userForm.getName(), userForm.getPhone());
        userService.saveUser(user);
    }

    @PostMapping("/booking")
    public ResponseEntity<String> doBooking(@RequestBody BookingForm bookingForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yy");
        LocalDate startDate = LocalDate.parse(bookingForm.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(bookingForm.getEndDate(), formatter);

        if (startDate.isBefore(LocalDate.now()) || startDate.compareTo(endDate) > 0) {
            return new ResponseEntity<>("date wrong", HttpStatus.OK);
        }

        List<Room> roomsFree = roomService.findRoomsFree(startDate, endDate);

        String roomStr = bookingForm.getRoom();
        Room room = roomService.findRoomByNumber(Integer.valueOf(roomStr));
        if (!roomsFree.contains(room)) {
            System.out.println("Room " + roomStr + " is busy at this dates");
            return new ResponseEntity<>("Room " + roomStr + " is busy at this dates", HttpStatus.OK);
        }
        String message = bookingService.reserveRoom(bookingForm);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("user_booking")
    public List<Booking> sendBookingByUser(@RequestParam("user") String username) {
        User user = userService.findUserByName(username);
        return bookingService.findBookingsByUser(user);
    }
}
