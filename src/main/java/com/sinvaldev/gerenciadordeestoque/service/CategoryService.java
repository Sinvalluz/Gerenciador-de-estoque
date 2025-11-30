package com.sinvaldev.gerenciadordeestoque.service;

import com.sinvaldev.gerenciadordeestoque.dto.CategoryCreateDTO;
import com.sinvaldev.gerenciadordeestoque.dto.CategoryDTO;
import com.sinvaldev.gerenciadordeestoque.model.Category;
import com.sinvaldev.gerenciadordeestoque.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO createCategory(CategoryCreateDTO dto) {
        Category category = new Category(dto.name());
        Category saved = categoryRepository.save(category);

        return new CategoryDTO(saved.getId(), saved.getName());
    }

    public List<CategoryDTO> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryDTO(category.getId(), category.getName()))
                .toList();
    }

    public CategoryDTO findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return new CategoryDTO(category.getId(), category.getName());
    }

    public CategoryDTO updateCategory(Integer id, CategoryCreateDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        category.setName(dto.name());

        Category updatedCategory = categoryRepository.save(category);
        return new CategoryDTO(updatedCategory.getId(), updatedCategory.getName());
    }
}
