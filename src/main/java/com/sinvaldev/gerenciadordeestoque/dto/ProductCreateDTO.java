package com.sinvaldev.gerenciadordeestoque.dto;

public record ProductCreateDTO(
        String name,
        Integer quantity,
        CategoryDTO categoryId
) {
}
