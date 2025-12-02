package com.sinvaldev.gerenciadordeestoque.service;
import com.sinvaldev.gerenciadordeestoque.dto.CategoryDTO;
import com.sinvaldev.gerenciadordeestoque.dto.CategoryUpdateDTO;
import com.sinvaldev.gerenciadordeestoque.entity.Category;
import com.sinvaldev.gerenciadordeestoque.mapper.CategoryMapper;
import com.sinvaldev.gerenciadordeestoque.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toEntity(categoryDTO);

        categoryRepository.findByName(category.getName())
                        .ifPresent(c -> {
                            throw new RuntimeException("Já existe uma categoria com esse nome: " + c.getName());
                        });

       return CategoryMapper.toCategoryDto(categoryRepository.save(category));
    }

    public List<CategoryDTO> findAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDto).toList();
    }

    public CategoryDTO updateCategory(Integer id, CategoryUpdateDTO categoryUpdateDTO) {
        Category categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Category categoryUpdated = Category.builder().id(categoryEntity.getId()).name(categoryUpdateDTO.name() != null ? categoryUpdateDTO.name() : categoryEntity.getName()).build();
        return CategoryMapper.toCategoryDto(categoryRepository.save(categoryUpdated));
    }

    public void delete(Integer id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoryRepository.deleteById(id);
    }
}
