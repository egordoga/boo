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
       /* Room room;
        List<Room> rooms = new ArrayList<>();*/
        Option option;
        List<Option> options = new ArrayList<>();
        User user = userRepository.findByName(bookingForm.getUser());
        if (user == null) {
            return "Такого клиента не найдено. Зарегестрируйтесь";
        }

        //Остальные проверки аналогичны и проигнорированы сознательно

        //for (String sr : bookingForm.getRooms()) {
            Room room = roomRepository.findByNumber(Integer.valueOf(bookingForm.getRoom()));
            for (String strOpt : bookingForm.getOptions()) {
                option = optionRepository.findByName(strOpt);

                System.out.println(option.toString());
                if (option == null) {
                    System.out.println("Option wrang");
                }

                options.add(option);
            }
            //room.setOptions(options);
            //rooms.add(room);
       // }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yy");
        LocalDate startDate = LocalDate.parse(bookingForm.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(bookingForm.getEndDate(), formatter);
        if (startDate.isBefore(LocalDate.now()) || startDate.compareTo(endDate) > 0) {
            return "Проверьте даты";
        }

        Booking booking = new Booking(startDate, endDate, user, room, options);
        bookingRepository.save(booking);

        return "Бронирование прошло успешно";
    }

    @Override
    public List<Booking> findBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findBooking(Long bid) {
        return bookingRepository.findById(bid).orElse(null);
    }

    @Override
    public List<Booking> findBookingsFuture() {
        return bookingRepository.findAllByEndDateAfter(LocalDate.now());
    }

//YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY



    public List<Booking> findAllReserved(LocalDate startDate, LocalDate endDate){
        return bookingRepository.findAllReserved(startDate, endDate);

    }
}
