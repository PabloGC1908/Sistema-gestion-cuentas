package com.pgc.app.controller;

import com.pgc.app.exception.classes.ResourceNotFoundException;
import com.pgc.app.model.Category;
import com.pgc.app.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getCategories();

        if (categories.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Byte id) {
        try {
            return ResponseEntity.ok(categoryService.getCategory(id));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<String> registerCategory(@RequestBody Category category) {
        String response = categoryService.registerCategory(category);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/list")
    public ResponseEntity<String> registerCategories(@RequestBody List<Category> categories) {
        String response = categoryService.registerCategories(categories);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Byte id) {
        String response = categoryService.deleteCategory(id);

        return ResponseEntity.ok(response);
    }
}
