package com.dog.restful.api.repository;

import com.dog.restful.api.model.Breed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreedRepository extends CrudRepository<Breed, Long> {

    @Query("FROM Breed WHERE name = ?1")
    Breed findByName (String breedName);
}
