package com.sinvaldev.gerenciadordeestoque.dto;

public record ProductDTO(
        Integer id,
        String name,
        Integer quantity,
        CategoryDTO categoryId
) {
}
