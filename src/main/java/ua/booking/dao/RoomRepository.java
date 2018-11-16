package ua.booking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.booking.entity.Category;
import ua.booking.entity.Room;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByCategory(Category category);

    Room findByNumber(Integer number);

    //List<Room> findAllByBo

    @Query(value = "select r.* from room r where r.id not in(" +
            " select b.id from booking b where start_date <= :endDate and b.end_date > :startDate)", nativeQuery = true)
    List<Room> findAllNoReserved(@Param("startDate")LocalDate startDate, @Param("endDate") LocalDate endDate);
}
