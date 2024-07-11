package com.generation.gameWinner.repository;

import com.generation.gameWinner.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p, c.name AS nameCategory FROM Product p JOIN p.category c")
    public List<Product> findAllProductsWithNameCategory();
}
