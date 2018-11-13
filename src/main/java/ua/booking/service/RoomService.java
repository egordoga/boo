package ua.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.booking.dao.RoomRepository;
import ua.booking.entity.Category;
import ua.booking.entity.Room;

import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> findRoomsByCategory(Category category) {
        return roomRepository.findAllByCategory(category);
    }
}
