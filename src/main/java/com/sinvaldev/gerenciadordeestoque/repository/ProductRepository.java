package com.sinvaldev.gerenciadordeestoque.repository;

import com.sinvaldev.gerenciadordeestoque.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(Integer id);
}
