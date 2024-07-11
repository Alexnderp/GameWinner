package com.generation.gameWinner.controller;

import com.generation.gameWinner.model.Product;
import com.generation.gameWinner.repository.CategoryRepository;
import com.generation.gameWinner.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ProdutcController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productRepository.findAllProductsWithNameCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getById(@PathVariable UUID id){
        if(productRepository.existsById(id)){
            return ResponseEntity.ok(productRepository.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        if (categoryRepository.existsById(product.getCategory().getId())){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(productRepository.save(product));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inválida", null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update (@PathVariable UUID id, @RequestBody Product product) {

        if(productRepository.existsById(id)){
            if (categoryRepository.existsById(product.getCategory().getId())){
                return ResponseEntity.ok().body(productRepository.save(product));
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inválida", null);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        if(!productRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

}
