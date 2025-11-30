package com.sinvaldev.gerenciadordeestoque.dto;

public record ProductUpdateDTO(
        String name,
        Integer quantity,
        CategoryDTO categoryId
) {
}
