package com.store.pet.petstoreApi.controller;

import com.store.pet.petstoreApi.exception.CategoryException;
import com.store.pet.petstoreApi.exception.PetException;
import com.store.pet.petstoreApi.model.Pet;
import com.store.pet.petstoreApi.model.Status;
import com.store.pet.petstoreApi.repository.PetRepository;
import com.store.pet.petstoreApi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;


    //Add new pet POST
    @PostMapping("/add")
    public Pet addNewPet(@RequestBody Pet pet) throws PetException, CategoryException {
         return petService.savePet(pet);
    }
    //Update Existing pet PUT
    @PutMapping("/add")
    @CachePut(value = "pets",key ="#id")
    public Pet updatePet(@RequestBody Pet pet) throws PetException, CategoryException {
        return petService.updatePet(pet);
    }
    //get pets by status GET
    @GetMapping("/get")
    public List<Pet> getPetByStatus(@RequestBody List<Status> status) throws PetException{
        return petService.getPetByStatus(status);
    }
    // get pet by id GET
    @GetMapping("/{id}")
    @Cacheable(value = "pets",key = "#id")
    public  Pet getPetByID(@PathVariable Long id) throws PetException {
        return petService.getPetById(id);
    }
    // Update pet Post path variable id
    @PostMapping("/{id}")
    @CachePut(value = "pets",key = "#id")
    public Pet updateNameAndStatus(@PathVariable Long id,
                                   @RequestParam String name,
                                   @RequestParam Status status) throws PetException{
        return petService.updatePet(id,name,status);
    }




}
