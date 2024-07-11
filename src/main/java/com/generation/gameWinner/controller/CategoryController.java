package com.generation.gameWinner.controller;

import com.generation.gameWinner.model.Category;
import com.generation.gameWinner.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    ResponseEntity<List<Category>> getCategory(){
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Category>> getCatgoryById(@PathVariable Long id){
        if (categoryRepository.existsById(id)){
            return ResponseEntity.ok(categoryRepository.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<Category> create(@RequestBody Category category){
       return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/{id}")
    ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
            return categoryRepository.findById(id)
                    .map(response -> ResponseEntity.status(HttpStatus.CREATED)
                            .body(categoryRepository.save(category)))
                    .orElse(ResponseEntity.notFound().build());

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Category> tema = categoryRepository.findById(id);

        if (tema.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoryRepository.deleteById(id);
    }
}
