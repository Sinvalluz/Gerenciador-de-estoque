package com.sinvaldev.gerenciadordeestoque.service;

import com.sinvaldev.gerenciadordeestoque.dto.ProductDTO;
import com.sinvaldev.gerenciadordeestoque.dto.ProductUpdateDTO;
import com.sinvaldev.gerenciadordeestoque.entity.Product;
import com.sinvaldev.gerenciadordeestoque.mapper.CategoryMapper;
import com.sinvaldev.gerenciadordeestoque.mapper.ProductMapper;
import com.sinvaldev.gerenciadordeestoque.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct (ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);

        productRepository.findByName(product.getName())
                .ifPresent(p -> {
                    throw new RuntimeException("Já existe um produto cadastrado com o nome: " + p.getName());
                });
       return ProductMapper.toProductDto(productRepository.save(product));
    }

    public List<ProductDTO> findAllProducts(){
        return productRepository.findAll().stream().map(ProductMapper::toProductDto).toList();
    }

    public List<ProductDTO> findByCategory(Integer id) {
        List<ProductDTO> products = productRepository.findByCategoryId(id).stream().map(ProductMapper::toProductDto).toList();

        if (products.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado para a categoria ID: " + id);
        }

        return products;
    }

    public ProductDTO updateProduct(Integer id, ProductUpdateDTO productUpdateDTO) {
        Product productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum produto encontrado para a categoria ID: " + id));

        Product productUpdated = Product.builder()
                .id(productEntity.getId())
                .name(productUpdateDTO.name() != null ? productUpdateDTO.name() : productEntity.getName())
                .quantity(productUpdateDTO.quantity() != null ? productUpdateDTO.quantity() : productEntity.getQuantity())
                .category(productUpdateDTO.categoryId() != null ? CategoryMapper.toEntity(productUpdateDTO.categoryId()) : productEntity.getCategory()).build();

        return ProductMapper.toProductDto(productRepository.save(productUpdated));
    }

    public void delete(Integer id) {
        productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum produto encontrado com o id: " + id));

        productRepository.deleteById(id);
    }

}
