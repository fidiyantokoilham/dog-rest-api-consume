package com.dog.restful.api.service;

import com.dog.restful.api.model.Breed;
import com.dog.restful.api.model.Image;
import com.dog.restful.api.model.SubBreed;
import com.dog.restful.api.model.dto.BreedDto;
import com.dog.restful.api.model.exception.ItemIsAlreadyAssignedException;
import com.dog.restful.api.model.exception.ItemNotFoundException;
import com.dog.restful.api.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BreedService {


    private final BreedRepository breedRepository;
    private final SubBreedService subBreedService;

    @Autowired
    public BreedService(BreedRepository breedRepository, SubBreedService subBreedService) {
        this.breedRepository = breedRepository;
        this.subBreedService = subBreedService;
    }

    public Breed addBreed(BreedDto breedDto) {
        Breed breed = new Breed();
        breed.setName(breedDto.getBreadName());

        List<SubBreed> subBreeds = new ArrayList<>();
        List<Image> images = new ArrayList<>();

        breedDto.getSubBreed().forEach((subBreedValue) -> {
            SubBreed subBreed = new SubBreed();
            subBreed.setSubBreedName(subBreedValue.getSubBreedName());
            subBreeds.add(subBreed);
        });
        breedDto.getImage().forEach((imageUrl) -> {
            Image image = new Image();
            image.setUrl(imageUrl.getUrl());
            images.add(image);
        });
        breed.getSubBreeds().addAll(subBreeds);
        breed.getImages().addAll(images);
        return breedRepository.save(breed);
    }


    public List<Breed> getBreeds() {
        return StreamSupport.stream(breedRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }


    public Breed deleteBreed(String breedName) {
        Breed breed = new Breed();
        breed = breedRepository.findByName(breedName);
        breedRepository.delete(breed);
        return breed;
    }

    @Transactional
    public Breed editBreed(String breedName, String newBreedName) {
        Breed breedToEdit = new Breed();
        breedToEdit =  breedRepository.findByName(breedName);
        breedToEdit.setName(newBreedName);
        return breedToEdit;
    }

    public void addBreedFromRest(BreedDto breedDto) {
        Breed breed = new Breed();
        breed.setName(breedDto.getBreadName());

        List<SubBreed> subBreeds = new ArrayList<>();
        List<Image> images = new ArrayList<>();

        breedDto.getSubBreed().forEach((subBreedValue) -> {
            SubBreed subBreed = new SubBreed();
            subBreed.setSubBreedName(subBreedValue.getSubBreedName());
            subBreeds.add(subBreed);
        });
        breedDto.getImage().forEach((imageUrl) -> {
            Image image = new Image();
            image.setUrl(imageUrl.getUrl());
            images.add(image);
        });
        breed.getSubBreeds().addAll(subBreeds);
        breed.getImages().addAll(images);
        breedRepository.save(breed);
    }
}
