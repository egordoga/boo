package ua.booking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;
    private BigDecimal price;

    @OneToMany(mappedBy = "room")
    @JsonManagedReference(value = "room")
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "category")
    private Category category;
}
