package ua.booking.service;

import ua.booking.entity.Category;
import ua.booking.entity.Room;

import java.util.List;

public interface IRoomService {
    List<Room> findRoomsByCategory(Category category);
}
