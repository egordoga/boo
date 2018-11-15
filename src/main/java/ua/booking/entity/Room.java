package ua.booking.entity;

import lombok.Data;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer number;
    private BigDecimal price;
    //private Byte isFree;

   /* @ManyToMany(mappedBy = "rooms")
    private List<Option> options;*/

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


   /* public Room(Integer number, String category, BigDecimal price) {
        this.number = number;
        this.category = category;
        this.price = price;
    }*/
}
