package com.sinvaldev.gerenciadordeestoque.mapper;

import com.sinvaldev.gerenciadordeestoque.dto.CategoryDTO;
import com.sinvaldev.gerenciadordeestoque.dto.ProductDTO;
import com.sinvaldev.gerenciadordeestoque.entity.Category;
import com.sinvaldev.gerenciadordeestoque.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductDTO productDTO) {
        return new Product(
                productDTO.id(),
                productDTO.name(),
                productDTO.quantity(),
                new Category(
                        productDTO.categoryId().id(),
                        productDTO.categoryId().name()));
    }

    public static ProductDTO toProductDto(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                new CategoryDTO(
                        product.getCategory().getId(),
                        product.getCategory().getName()));
    }
}
