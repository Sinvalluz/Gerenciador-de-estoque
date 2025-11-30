package com.sinvaldev.gerenciadordeestoque.repository;

import com.sinvaldev.gerenciadordeestoque.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String name);

    List<Product> findByCategoryId(Integer id);
}
