package ua.booking.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "room_option", joinColumns = {@JoinColumn(name = "option_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id")})
    private List<Booking> bookings;
}