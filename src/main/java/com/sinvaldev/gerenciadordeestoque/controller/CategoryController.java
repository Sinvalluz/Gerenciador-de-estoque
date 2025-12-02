package com.sinvaldev.gerenciadordeestoque.controller;

import com.sinvaldev.gerenciadordeestoque.dto.CategoryCreateDTO;
import com.sinvaldev.gerenciadordeestoque.dto.CategoryDTO;
import com.sinvaldev.gerenciadordeestoque.dto.CategoryUpdateDTO;
import com.sinvaldev.gerenciadordeestoque.entity.Category;
import com.sinvaldev.gerenciadordeestoque.mapper.CategoryMapper;
import com.sinvaldev.gerenciadordeestoque.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category(categoryCreateDTO.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(CategoryMapper.toCategoryDto(category)));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listAll() {
        return  ResponseEntity.ok(categoryService.findAllCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryUpdateDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        categoryService.delete(id);
    }
}
