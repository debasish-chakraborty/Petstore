package com.store.pet.petstoreApi.service.impl;

import com.store.pet.petstoreApi.exception.CategoryException;
import com.store.pet.petstoreApi.exception.PetException;
import com.store.pet.petstoreApi.model.Category;
import com.store.pet.petstoreApi.model.Pet;
import com.store.pet.petstoreApi.model.Status;
import com.store.pet.petstoreApi.repository.PetRepository;
import com.store.pet.petstoreApi.service.CategoryService;
import com.store.pet.petstoreApi.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    CategoryService categoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PetServiceImpl.class);

    @Override
    /*this method will add new pet along with category if new category is added */
    public Pet savePet(Pet pet) throws PetException, CategoryException {

        Category category = categoryService.saveCategory(pet.getCategory());

        Pet petbody = Pet.builder()
                .category(pet.getCategory())
                .photoUrls(pet.getPhotoUrls())
                .name(pet.getName())
                .status(Status.AVAILABLE).build();
        return petRepository.save(petbody);
    }

    @Override
    /*this will update existing pet*/
    public Pet updatePet(Pet pet) throws PetException,CategoryException {
        Category category = pet.getCategory();
        LOGGER.info("request category:{}",category.getId());
        if(!Objects.nonNull(category.getId()) || !Objects.nonNull(pet.getId())
                || !Objects.nonNull(pet.getName()) || !Objects.nonNull(pet.getStatus())) {
            throw new PetException(405,"category id,pet id,pet name, status cannot be null");
        }
        if(!Arrays.asList(Status.values()).contains(pet.getStatus())) {
            throw new PetException(405,"Invalid Status, Can be either AVAILABLE,PENDING,SOLD");
        }
        Category getCategory = null;
        try {
            getCategory = categoryService.getCategoryById(category.getId());
            LOGGER.info("get Category{},name: {}", getCategory.getId(),getCategory.getName());
        } catch (CategoryException e) {
            throw new RuntimeException(e);
        }
        if(!Objects.nonNull(getCategory)) {
            throw new CategoryException(404, "Category NOt Found.Please add The category First to update");
        }
        if (!petRepository.existsById(pet.getId())) {
            throw new PetException(400,"Pet Id is invalid.No record Found with given id");
        }
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getPetByStatus(List<Status> statusList) throws PetException {
        List<Status> allowedStatus = Arrays.asList(Status.values());
        statusList.retainAll(allowedStatus);
        System.out.println("status list" + statusList);
        List<String> statusStringList = statusList.stream().map(status -> status.name()).collect(Collectors.toList());
        List<Pet> petList = petRepository.findAll();
        return  petList.stream().filter(pet -> statusStringList.contains(pet.getStatus().name())).collect(Collectors.toList());

    }

    @Override
    public Pet getPetById(Long id) throws PetException {
        return petRepository.getById(id);
    }

    @Override
    public Pet updatePet(Long id, String name, Status status) throws PetException {
        Pet pet = petRepository.getById(id);
        pet.setName(name);
        pet.setStatus(status);
       return petRepository.save(pet);
    }
}