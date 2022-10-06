package com.store.pet.petstoreApi.service.impl;

import com.store.pet.petstoreApi.exception.CategoryException;
import com.store.pet.petstoreApi.model.Category;
import com.store.pet.petstoreApi.repository.CategoryRepository;
import com.store.pet.petstoreApi.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired(required = true)
    CategoryRepository categoryRepository;
    @Override
    public Category saveCategory(Category category) throws CategoryException {
        LOGGER.info("inside method save category");
        return categoryRepository.save(category);
    }

    @Override
    public void removeCategory(Long id) throws CategoryException {
         categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(Long id) throws CategoryException {
        return categoryRepository.getById(id);
    }

    @Override
    public List<Category> getAllCategory() throws CategoryException {
        return categoryRepository.findAll();
    }
}
