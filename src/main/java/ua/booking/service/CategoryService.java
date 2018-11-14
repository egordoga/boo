package ua.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.booking.dao.CategoryRepository;
import ua.booking.entity.Category;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
}
