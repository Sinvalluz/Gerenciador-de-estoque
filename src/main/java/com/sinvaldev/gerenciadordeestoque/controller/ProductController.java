package com.sinvaldev.gerenciadordeestoque.controller;

import com.sinvaldev.gerenciadordeestoque.dto.CategoryDTO;
import com.sinvaldev.gerenciadordeestoque.dto.ProductCreateDTO;
import com.sinvaldev.gerenciadordeestoque.dto.ProductDTO;
import com.sinvaldev.gerenciadordeestoque.dto.ProductUpdateDTO;
import com.sinvaldev.gerenciadordeestoque.entity.Category;
import com.sinvaldev.gerenciadordeestoque.entity.Product;
import com.sinvaldev.gerenciadordeestoque.mapper.ProductMapper;
import com.sinvaldev.gerenciadordeestoque.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        Product product = new Product(productCreateDTO.name(), productCreateDTO.quantity(), new Category(productCreateDTO.categoryId().id(), productCreateDTO.categoryId().name()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(ProductMapper.toProductDto(product)));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listAll () {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<ProductDTO>> listByCategory(@RequestParam Integer categoryId) {
        return ResponseEntity.ok(productService.findByCategory(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody ProductUpdateDTO productUpdateDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
