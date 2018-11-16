package ua.booking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference(value = "room")
    private Room room;

    @ManyToMany(mappedBy = "bookings")
    @JsonManagedReference(value = "opt")
    private List<Option> options;

    public Booking(LocalDate startDate, LocalDate endDate, User user, Room room, List<Option> options) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.room = room;
        this.options = options;
    }
}
