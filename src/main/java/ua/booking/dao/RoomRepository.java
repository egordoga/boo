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

    @Query(value = "select r.* from room r where r.id not in (select b.room_id from booking b where" +
            " (:startDate >= b.start_date and :startDate < b.end_date) or" +
            " (:endDate > b.start_date and :endDate <= b.end_date) or" +
            " (:startDate <= b.start_date and :endDate >= b.end_date))", nativeQuery = true)
    List<Room> findAllNoReserved(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
