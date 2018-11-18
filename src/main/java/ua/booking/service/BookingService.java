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
import ua.booking.model.ReservedForm;

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
    public List<Booking> findBookingsByUser(User user) {
        return bookingRepository.findAllByUser(user);
    }

    @Override
    public ReservedForm reserveRoom(BookingForm bookingForm) {

        Option option;
        List<Option> options = new ArrayList<>();
        User user = userRepository.findByName(bookingForm.getUser());
        if (user == null) {
            return new ReservedForm(false, "User not find");
        }

        //Остальные проверки аналогичны и проигнорированы сознательно

        Room room = roomRepository.findByNumber(Integer.valueOf(bookingForm.getRoom()));
        for (String strOpt : bookingForm.getOptions()) {
            option = optionRepository.findByName(strOpt);
            options.add(option);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yy");
        LocalDate startDate = LocalDate.parse(bookingForm.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(bookingForm.getEndDate(), formatter);
        if (startDate.isBefore(LocalDate.now()) || startDate.compareTo(endDate) > 0) {
            return new ReservedForm(false, "date wrong");
        }

        Booking booking = new Booking(startDate, endDate, user, room, options);
        bookingRepository.save(booking);

        return new ReservedForm(true, "Booking successfully");
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
}
