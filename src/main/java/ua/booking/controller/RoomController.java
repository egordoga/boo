package ua.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    private RoomService roomService;
    private CategoryService categoryService;

    @Autowired
    public RoomController(RoomService roomService, CategoryService categoryService) {
        this.roomService = roomService;
        this.categoryService = categoryService;
    }


    @GetMapping("/free/{start}/{end}")
    public List<Room> sendFreeRooms(@PathVariable("start") String start,
                                    @PathVariable("end") String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yy");
        LocalDate startDate = LocalDate.parse(start, formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);

        return roomService.findRoomsFree(startDate, endDate);
    }

    @GetMapping("/category/{name}")
    public List<Room> sendByCategory(@PathVariable("name") String categoryName) {
        Category category = categoryService.findCategoryByName(categoryName);

        return roomService.findRoomsByCategory(category);
    }
}
