package com.dog.restful.api.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dog.restful.api.model.Breed;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BreedDto {
    private Long id;
    @NotNull(message = "Breed Name cannot be null")
    private String breadName;
    private List<SubBreedDto> subBreed = new ArrayList<>();
    private List<ImageDto> image = new ArrayList<>();


    public static BreedDto from(Breed breed){
        BreedDto breedDto = new BreedDto();
        breedDto.setId(breed.getId());
        breedDto.setBreadName(breed.getName());
        breedDto.setSubBreed(breed.getSubBreeds().stream().map(SubBreedDto::from).collect(Collectors.toList()));
        breedDto.setImage(breed.getImages().stream().map(ImageDto::from).collect(Collectors.toList()));
        return breedDto;
    }
}
