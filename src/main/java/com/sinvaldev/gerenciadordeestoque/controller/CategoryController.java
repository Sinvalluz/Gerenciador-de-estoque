package com.sinvaldev.gerenciadordeestoque.controller;

import com.sinvaldev.gerenciadordeestoque.dto.CategoryCreateDTO;
import com.sinvaldev.gerenciadordeestoque.dto.CategoryDTO;
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
        return categoryService.createCategory(dto);
    }

    @GetMapping
    public List<CategoryDTO> listAll() {
        return categoryService.findAllCategories();
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Integer id, @RequestBody CategoryCreateDTO dto) {
        return categoryService.updateCategory(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        categoryService.delete(id);
    }
}
