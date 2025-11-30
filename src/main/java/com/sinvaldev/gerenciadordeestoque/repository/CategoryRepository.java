package com.sinvaldev.gerenciadordeestoque.repository;

import com.sinvaldev.gerenciadordeestoque.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
