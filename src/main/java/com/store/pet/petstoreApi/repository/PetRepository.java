package com.store.pet.petstoreApi.repository;

import com.store.pet.petstoreApi.model.Category;
import com.store.pet.petstoreApi.model.Pet;
import com.store.pet.petstoreApi.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {

}
