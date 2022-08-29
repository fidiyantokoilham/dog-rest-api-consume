package com.dog.restful.api.repository;

import com.dog.restful.api.model.Image;
import com.dog.restful.api.model.SubBreed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository <Image, Long> {

    @Query(value = "SELECT URL FROM IMAGE ORDER BY RANDOM() LIMIT 1" ,nativeQuery = true)
    String findRandomImage();

    @Query(value = "SELECT URL FROM IMAGE A JOIN BREED B ON A.FK_BREED_ID = B.BREED_ID WHERE  B.NAME = ?1" ,nativeQuery = true)
    List<String> findImagegByBreed (String breedName);
}
