package com.store.pet.petstoreApi.service;

import com.store.pet.petstoreApi.exception.CategoryException;
import com.store.pet.petstoreApi.exception.PetException;
import com.store.pet.petstoreApi.model.Pet;
import com.store.pet.petstoreApi.model.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    Pet savePet(Pet pet) throws PetException, CategoryException;
    Pet updatePet(Pet pet) throws  PetException, CategoryException;
    List<Pet> getPetByStatus(List<Status> statusList) throws PetException;
    Pet getPetById(Long id) throws PetException;
    Pet updatePet(Long id, String name, Status status) throws PetException;
}
