package com.sinvaldev.gerenciadordeestoque.service;
import com.sinvaldev.gerenciadordeestoque.entity.Category;
import com.sinvaldev.gerenciadordeestoque.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category) {

        categoryRepository.findByName(category.getName())
                        .ifPresent(c -> {
                            throw new RuntimeException("Já existe uma categoria com esse nome: " + c.getName());
                        });

       categoryRepository.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Integer id, Category category) {
        Category categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Category categoryUpdated = Category.builder().id(categoryEntity.getId()).name(category.getName()).build();
        return categoryRepository.save(categoryUpdated);
    }

    public void delete(Integer id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoryRepository.deleteById(id);
    }
}
