package ua.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.booking.entity.Booking;
import ua.booking.entity.Room;
import ua.booking.entity.User;
import ua.booking.model.BookingForm;
import ua.booking.model.ReservedForm;
import ua.booking.model.UserForm;
import ua.booking.service.BookingService;
import ua.booking.service.RoomService;
import ua.booking.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private RoomService roomService;
    private BookingService bookingService;

    @Autowired
    public UserController(UserService userService, RoomService roomService, BookingService bookingService) {
        this.userService = userService;
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserForm> addUser(@RequestBody UserForm userForm) {
        User user = new User(userForm.getName(), userForm.getPhone());
        userService.saveUser(user);
        return new ResponseEntity<>(userForm, HttpStatus.OK);
    }

    @PostMapping("/booking")
    public ResponseEntity<ReservedForm> addBooking(@RequestBody BookingForm bookingForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yy");
        LocalDate startDate = LocalDate.parse(bookingForm.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(bookingForm.getEndDate(), formatter);

        if (startDate.isBefore(LocalDate.now()) || startDate.compareTo(endDate) > 0) {
            return new ResponseEntity<>(new ReservedForm(false, "date wrong"), HttpStatus.OK);
        }

        List<Room> roomsFree = roomService.findRoomsFree(startDate, endDate);

        String roomStr = bookingForm.getRoom();
        Room room = roomService.findRoomByNumber(Integer.valueOf(roomStr));
        if (!roomsFree.contains(room)) {
            return new ResponseEntity<>(new ReservedForm(false, "Room " + roomStr + " is busy at this dates"), HttpStatus.OK);
        }
        return new ResponseEntity<>(bookingService.reserveRoom(bookingForm), HttpStatus.OK);
    }

    @GetMapping("user_booking/{username}")
    public List<Booking> sendBookingByUser(@PathVariable("username") String username) {
        User user = userService.findUserByName(username);
        return bookingService.findBookingsByUser(user);
    }
}
