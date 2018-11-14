package ua.booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class BookingForm {

    private String user;
    private List<String> rooms;
    private String startDate;
    private String endDate;
    private List<String> options;
}
