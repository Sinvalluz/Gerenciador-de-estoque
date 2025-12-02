package com.sinvaldev.gerenciadordeestoque.mapper;

import com.sinvaldev.gerenciadordeestoque.dto.CategoryDTO;
import com.sinvaldev.gerenciadordeestoque.entity.Category;
import com.sinvaldev.gerenciadordeestoque.entity.Product;

public class CategoryMapper {
    public static Category toEntity(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.id(), categoryDTO.name());
    }

    public static CategoryDTO toCategoryDto (Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
