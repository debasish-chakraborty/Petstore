package com.store.pet.petstoreApi.controller;

import com.store.pet.petstoreApi.exception.CategoryException;
import com.store.pet.petstoreApi.model.Category;
import com.store.pet.petstoreApi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public Category saveCategory(@RequestBody Category category) throws CategoryException {
        return categoryService.saveCategory(category);
    }
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) throws CategoryException{
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/getAll")
    public List<Category> getAllCategory() throws CategoryException{
        return categoryService.getAllCategory();
    }

}
