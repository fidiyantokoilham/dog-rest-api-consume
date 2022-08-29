package com.dog.restful.api.service;

import com.dog.restful.api.model.SubBreed;
import com.dog.restful.api.model.exception.ItemNotFoundException;
import com.dog.restful.api.repository.SubBreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubBreedService {

    private final SubBreedRepository subBreedRepository;

    @Autowired
    public SubBreedService(SubBreedRepository subBreedRepository) {
        this.subBreedRepository = subBreedRepository;
    }

    public List<SubBreed> getsubBreedByName(String breedName) {
        List<SubBreed> subBreed = new ArrayList<>();
        subBreed = subBreedRepository.findByName(breedName);
        return subBreed;
    }

    public SubBreed addSubreed(SubBreed subBreed) {
        return subBreedRepository.save(subBreed);
    }


}
