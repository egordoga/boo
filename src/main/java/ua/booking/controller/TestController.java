package ua.booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.booking.entity.Room;

import java.math.BigDecimal;

@RestController
public class TestController {

    @GetMapping("/test")
    public Room sendRoom() {
        return new Room(5, "lux", new BigDecimal("77"));
    }
}
