package ua.booking.service;

import ua.booking.entity.Category;
import ua.booking.entity.Room;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;

public interface IRoomService {
    List<Room> findRoomsByCategory(Category category);

    List<Room> findRoomsFree(LocalDate startDate, LocalDate endDate);

    Room findRoomByNumber(Integer number);
}
