package com.store.pet.petstoreApi.service;

import com.store.pet.petstoreApi.exception.CategoryException;
import com.store.pet.petstoreApi.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
@Service
public interface CategoryService {
    public Category saveCategory(Category category) throws CategoryException;
    public void removeCategory(Long id) throws CategoryException;

    public Category getCategoryById(Long id) throws CategoryException;

    List<Category> getAllCategory() throws CategoryException;
}
