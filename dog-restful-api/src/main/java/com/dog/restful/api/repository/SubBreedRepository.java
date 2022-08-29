package com.dog.restful.api.repository;

import com.dog.restful.api.model.Breed;
import com.dog.restful.api.model.SubBreed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubBreedRepository extends CrudRepository<SubBreed, Long> {

    @Query(value = "SELECT * FROM SUB_BREED A JOIN BREED B ON A.FK_BREED_ID = B.BREED_ID WHERE  B.NAME = ?1" ,nativeQuery = true)
    List<SubBreed> findByName (String breedName);
}
