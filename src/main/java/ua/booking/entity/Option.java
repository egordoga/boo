package ua.booking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "booking_option", joinColumns = {@JoinColumn(name = "option_id")},
            inverseJoinColumns = {@JoinColumn(name = "booking_id")})*/

    @ManyToMany(mappedBy = "options", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference(value = "opt")
    private List<Booking> bookings = new ArrayList<>();

    @Override
    public String toString() {
        return "Option{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
