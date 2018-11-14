package ua.booking.service;

import org.springframework.stereotype.Repository;
import ua.booking.entity.Category;

@Repository
public interface ICategoryService {
    Category findCategoryByName(String name);
}
