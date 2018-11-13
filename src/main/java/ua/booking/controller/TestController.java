package ua.booking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ua.booking.entity.Room;
import ua.booking.entity.User;

import java.math.BigDecimal;

@RestController
public class TestController {

    @GetMapping("/test")
    public Room sendRoom() {
        return new Room(/*5, "lux", new BigDecimal("77")*/);
    }


    @PostMapping("/test1")
    public void testObject(@RequestBody User user){
        /*RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.ge*/

        System.out.println("Username: " + user.getName() + " phone: " + user.getPhone());
        System.out.println("ID " + user.getId());

        //return null;
    }
}
