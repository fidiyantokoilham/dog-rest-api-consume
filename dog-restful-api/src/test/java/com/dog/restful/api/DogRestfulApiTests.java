package com.dog.restful.api;

import com.dog.restful.api.model.Breed;
import com.dog.restful.api.model.Image;
import com.dog.restful.api.model.SubBreed;
import com.dog.restful.api.repository.BreedRepository;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class DogRestfulApiTests {

	@Autowired
	private BreedRepository breedRepository;

	@Test
	public void testInsert(){
		Breed breed = new Breed();
		breed.setName("hound");

		List<SubBreed> subBreedList = new ArrayList<>();
		SubBreed subBreed1 = new SubBreed();
		subBreed1.setSubBreedName("afghan");
		SubBreed subBreed2 = new SubBreed();
		subBreed2.setSubBreedName("basset");

		subBreedList.add(subBreed1);
		subBreedList.add(subBreed2);

		List<Image> imagelist = new ArrayList<>();
		Image image1 = new Image();
		image1.setUrl("https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg");
		Image image2 = new Image();
		image2.setUrl("https://images.dog.ceo/breeds/hound-basset/n02088238_11113.jpg");

		imagelist.add(image1);
		imagelist.add(image2);

		breed.getSubBreeds().addAll(subBreedList);
		breed.getImages().addAll(imagelist);

		breedRepository.save(breed);

		assertNotNull(breedRepository.findByName("hound"));

	}

	@Test
	public void testGet(){
		Breed breed = breedRepository.findByName("hound");
		assertNotNull(breed);
	}

	@Test
	public void testUpdate(){
		Breed breed = breedRepository.findByName("hound");
		breed.setName("thunder");
		breedRepository.save(breed);
		assertNotNull(breedRepository.findByName("thunder"));
	}

	@Test
	public void testDelete(){
		Breed breed = breedRepository.findByName("thunder");
		breed.setName("thunder");
		breedRepository.save(breed);
		assertNull(breedRepository.findByName("thunder"));
	}

}
