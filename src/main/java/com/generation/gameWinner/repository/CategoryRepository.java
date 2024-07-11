package com.generation.gameWinner.repository;

import com.generation.gameWinner.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
