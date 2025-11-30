package com.sinvaldev.gerenciadordeestoque.controller;

import com.sinvaldev.gerenciadordeestoque.dto.CategoryCreateDTO;
import com.sinvaldev.gerenciadordeestoque.dto.CategoryDTO;
import com.sinvaldev.gerenciadordeestoque.dto.CategoryUpdateDTO;
import com.sinvaldev.gerenciadordeestoque.entity.Category;
import com.sinvaldev.gerenciadordeestoque.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryDTO createCategory(@RequestBody CategoryCreateDTO dto) {
        Category category = new Category(dto.name());
        categoryService.createCategory(category);
        return  new CategoryDTO(category.getId(), category.getName());
    }

    @GetMapping
    public List<CategoryDTO> listAll() {
        List<Category> categories = categoryService.findAllCategories();
        return  categories.stream().map(category -> new CategoryDTO(category.getId(), category.getName())).toList();
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Integer id, @RequestBody CategoryUpdateDTO dto) {
        Category category = new Category(dto.name());
        Category updatedCategory = categoryService.updateCategory(id, category);

        return new CategoryDTO(updatedCategory.getId(), updatedCategory.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        categoryService.delete(id);
    }
}
