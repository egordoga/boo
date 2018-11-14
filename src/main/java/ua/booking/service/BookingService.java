package ua.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.booking.dao.BookingRepository;
import ua.booking.dao.OptionRepository;
import ua.booking.dao.RoomRepository;
import ua.booking.dao.UserRepository;
import ua.booking.entity.Booking;
import ua.booking.entity.Option;
import ua.booking.entity.Room;
import ua.booking.entity.User;
import ua.booking.model.BookingForm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OptionRepository optionRepository;

    @Override
    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findBookingsByUser(User user) {
        return bookingRepository.findAllByUser(user);
    }

    @Override
    public String reserveRoom(BookingForm bookingForm) {
        Room room;
        List<Room> rooms = new ArrayList<>();
        Option option;
        List<Option> options = new ArrayList<>();
        User user = userRepository.findByName(bookingForm.getUser());
        if (user == null) {
            return "Такого клиента не найдено. Зарегестрируйтесь";
        }

        //Остальные проверки аналогичны и проигнорированы сознательно

        for (String s : bookingForm.getRooms()) {
            room = roomRepository.findByNumber(Integer.valueOf(s));
            rooms.add(room);
        }

        for (String s : bookingForm.getOptions()) {
            option = optionRepository.findByName(s);
            options.add(option);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yy");
        LocalDate startDate = LocalDate.parse(bookingForm.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(bookingForm.getEndDate(), formatter);

        Booking booking = new Booking(startDate, endDate, user, rooms, options);
        bookingRepository.save(booking);

        return "Бронирование прошло успешно";
    }

    @Override
    public List<Booking> findBookings() {
        return bookingRepository.findAll();
    }
}
