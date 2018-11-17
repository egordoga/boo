package ua.booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user")
    private List<Booking> bookings;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
