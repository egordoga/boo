package ua.booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingForm {

    private String user;
    private String room;
    private String startDate;
    private String endDate;
    private ArrayList<String> options = new ArrayList<>();
}
