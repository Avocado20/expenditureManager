package service;

import domain.Category;
import dto.CategoryDto;

public interface CategoryService {
    Category create(CategoryDto categoryDto);
    Category getByName(String name);
    Iterable<Category> getAll();
    Category edit(Category categoryDto);
    Category findById(Long categoryId);
    void deleteByKey(Long categoryId);
}
