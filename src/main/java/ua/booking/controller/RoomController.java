package ua.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yy");
        LocalDate startDate = LocalDate.parse(start/*, formatter*/);
        LocalDate endDate = LocalDate.parse(end/*, formatter*/);

        System.out.println(roomService.findRoomsFree(startDate, endDate).toString());

        return roomService.findRoomsFree(startDate, endDate);
    }

    @GetMapping("/category")
    public List<Room> /*String*/ sendByCategory(@RequestParam("name") String categoryName) {


        System.out.println("HHHHHHHHHHHHHHHH " + categoryName);

        Category category = categoryService.findCategoryByName(categoryName);

        System.out.println("aaaaaaaaaaaaaaaaa Category " + category.getName());
        System.out.println(category.toString());
        System.out.println("List " + roomService.findRoomsByCategory(category).toString());

        //System.out.println("OOOOOOOOOO" + roomService.findRoomsByCategory(category).toString());
        return roomService.findRoomsByCategory(category);

       // return "GGGGGGGGGGGGGGG";
    }
}
