package ua.booking.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "booking")
    private List<Room> rooms;

    @ManyToMany(mappedBy = "bookings")
    private List<Option> options;

    public Booking(LocalDate startDate, LocalDate endDate, User user, List<Room> rooms, List<Option> options) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.rooms = rooms;
        this.options = options;
    }
}
