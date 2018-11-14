package ua.booking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.booking.entity.Option;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    Option findByName(String name);
}
