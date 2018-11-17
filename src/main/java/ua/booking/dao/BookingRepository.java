package ua.booking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.booking.entity.Booking;
import ua.booking.entity.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByUser(User user);

    List<Booking> findAllByEndDateAfter(LocalDate date);

    //List<Booking> findAllBy




    @Query(value = "select b.* from booking b where" +
            " (:startDate >= b.start_date and :startDate < b.end_date) or" +
            " (:endDate > b.start_date and :endDate <= b.end_date) or" +
            " (:startDate <= b.start_date and :endDate >= b.end_date)", nativeQuery = true)

    List<Booking> findAllReserved(@Param("startDate")LocalDate startDate, @Param("endDate") LocalDate endDate);
}
