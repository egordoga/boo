package ua.booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.booking.entity.Room;

import java.util.List;

@RestController
public class MainController {

    @GetMapping("/free")
    public List<Room> sendFreeRooms() {

    }
}
