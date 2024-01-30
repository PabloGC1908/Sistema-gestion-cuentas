package com.pgc.app.service;

import com.pgc.app.exception.classes.ResourceNotFoundException;
import com.pgc.app.model.Category;
import com.pgc.app.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Byte id) throws ResourceNotFoundException {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty())
            throw new ResourceNotFoundException("No se encontro la categoria con id: " + id);

        return category.get();
    }

    public String registerCategory(Category category) {
        try {
            if (Objects.equals(category.getCategory(), "NaN"))
                category.setCategory(null);

            categoryRepository.save(category);

            return "Categoria registrada correctamente";
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String registerCategories(List<Category> categories) {
        for (Category category: categories) {
            String response = registerCategory(category);

            if (!response.equals("Categoria registrada correctamente"))
                break;
        }

        return "Lista de categorias registradas correctamente";
    }

    public String deleteCategory(Byte id) {
        try {
            categoryRepository.deleteById(id);

            return "Categoria eliminada correctamente";
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
