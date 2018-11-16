package ua.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.booking.entity.Category;
import ua.booking.entity.Room;
import ua.booking.service.CategoryService;
import ua.booking.service.RoomService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/free")
    public List<Room> sendFreeRooms(@RequestParam("startDate") String start,
                                    @RequestParam("endDate") String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yy");
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);

        roomService.findRoomsFree(startDate, endDate).forEach(System.out::println);

        return roomService.findRoomsFree(startDate, endDate);
    }

    @GetMapping(path = "/category", produces="application/json")
    public List<Room>  sendByCategory(@RequestParam("name") String categoryName) {
        Category category = categoryService.findCategoryByName(categoryName);
        roomService.findRoomsByCategory(category).forEach(System.out::println);

        return roomService.findRoomsByCategory(category);
    }
}
