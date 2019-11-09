package service.impl;

import domain.Category;
import dto.CategoryDto;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;
import service.AbstractService;
import service.CategoryService;

import javax.inject.Inject;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl extends AbstractService implements CategoryService {

    @Override
    public Category create(CategoryDto categoryDto) {
        return categoryRepository.save(new Category().setName(categoryDto.getName()));
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(this.i18n.get("category.no.such.category.name", name)));
    }

    @Override
    public Iterable<Category> getAll() {

        return categoryRepository.findAll();
    }

    @Override
    public Category edit(Category categoryDto) {
        return categoryRepository.save(this.findById(categoryDto.getId()).setName(categoryDto.getName()));
    }

    @Override
    public Category findById(Long categoryId) {
        return this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException(this.i18n.get("category.no.such.category", String.valueOf(categoryId))));
    }

    @Override
    public void deleteByKey(Long categoryId) {
        if (this.categoryRepository.findById(categoryId).isPresent()) {
            this.categoryRepository.deleteById(categoryId);
        } else {
            new NoSuchElementException(this.i18n.get("category.no.such.category", String.valueOf(categoryId)));
        }
    }

    @Inject
    protected CategoryRepository categoryRepository;
}
