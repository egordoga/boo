package ua.booking.model;

import lombok.Data;
import ua.booking.entity.Room;

import java.util.List;

@Data
public class BookingForm {

    private String user;
    private String room;
    private String startDate;
    private String endDate;
    private List<String> options;
}
