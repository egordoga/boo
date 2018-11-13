package ua.booking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer number;
    private BigDecimal price;
    //private Byte isFree;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


   /* public Room(Integer number, String category, BigDecimal price) {
        this.number = number;
        this.category = category;
        this.price = price;
    }*/
}
