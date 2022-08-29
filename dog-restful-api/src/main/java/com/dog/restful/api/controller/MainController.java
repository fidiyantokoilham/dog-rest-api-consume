package com.dog.restful.api.controller;


import com.dog.restful.api.Constanta;
import com.dog.restful.api.model.Breed;
import com.dog.restful.api.model.SubBreed;
import com.dog.restful.api.model.dto.BreedDto;
import com.dog.restful.api.model.response.ImageResponse;
import com.dog.restful.api.model.response.BreedResponse;
import com.dog.restful.api.model.response.Response;
import com.dog.restful.api.model.response.SubBreedResponse;
import com.dog.restful.api.service.BreedService;
import com.dog.restful.api.service.DogApiService;
import com.dog.restful.api.service.ImageService;
import com.dog.restful.api.service.SubBreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/api")
@Api(value = "Resource", description = "Consume Data from dog.ceo and store to DB")
public class MainController {

    private final BreedService breedService;
    private final SubBreedService subBreedService;
    private final ImageService imageService;


    private static final String MAIN_PAGE = "";
    private static final String DOG_BREED_URL = "https://dog.ceo/api/breeds/list/all";

    @Autowired
    private DogApiService consumerService;

    public MainController(BreedService breedService, SubBreedService subBreedService, ImageService imageService) {
        this.breedService = breedService;
        this.subBreedService = subBreedService;
        this.imageService = imageService;
    }


    @GetMapping("/Consume")
    public String consumeData() {
        String output = consumerService.parse(DOG_BREED_URL).toString();
        System.err.println(output);
        return output;
    }

    @GetMapping("/getAllData")
    @Transactional(timeout = 5000)
    public ResponseEntity<List<BreedDto>> getAllData(){
        List<Breed> carts = breedService.getBreeds();
        List<BreedDto> cartsDto = carts.stream().map(BreedDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(cartsDto, HttpStatus.OK);
    }

    @GetMapping("/breeds/list/all")
    @Transactional(timeout = 5000)
    public ResponseEntity<BreedResponse> getListBreed() {
        List<Breed> carts = breedService.getBreeds();
        List<BreedDto> cartsDto = carts.stream().map(BreedDto::from).collect(Collectors.toList());
        HashMap<String, List<String>> output = new HashMap<>();

        cartsDto.forEach(breedDto -> {
            List<String> subbreeds = new ArrayList<>();
            breedDto.getSubBreed().forEach(listSubName -> {
                subbreeds.add(listSubName.getSubBreedName());
            });
            output.put(breedDto.getBreadName(), subbreeds);
        });

        BreedResponse response = new BreedResponse();
        response.setMessage(output);
        if (carts.size() != 0) {
            response.setStatus(Constanta.SUCCESS_STATUS);
        } else {
            response.setStatus(Constanta.NO_DATA_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/breeds/{breedName}/list")
    @Transactional(timeout = 2000)
    public ResponseEntity<SubBreedResponse> getSubBreedList(@PathVariable final String breedName) {
        List<SubBreed> subBreed = subBreedService.getsubBreedByName(breedName);
        List<String> output = new ArrayList<>();
        SubBreedResponse response = new SubBreedResponse();
        if (subBreed.size() != 0) {
            subBreed.forEach(subBreedName -> {
                output.add(subBreedName.getSubBreedName());
            });

            response.setMessage(output);
            response.setStatus(Constanta.SUCCESS_STATUS);
        } else {
            response.setMessage(new ArrayList<>());
            response.setStatus(Constanta.NO_DATA_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/breeds/imageg/random")
    public ResponseEntity<ImageResponse> getRandomImage() {
        ImageResponse response = new ImageResponse();
        List<String> imageurl = new ArrayList<>();
        imageurl.add(imageService.getRandomImage());
        if (imageurl != null) {
            response.setMessage(imageurl);
            response.setStatus(Constanta.SUCCESS_STATUS);
        } else {
            response.setMessage(new ArrayList<>());
            response.setStatus(Constanta.NO_DATA_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/breed/{breedName}/images")
    public ResponseEntity<ImageResponse> getImageByBreed(@PathVariable final String breedName) {
        ImageResponse response = new ImageResponse();
        List<String> imageurl = imageService.getImageByBreed(breedName);
        if (imageurl.size()!=0) {
            response.setMessage(imageurl);
            response.setStatus(Constanta.SUCCESS_STATUS);
        } else {
            response.setMessage(new ArrayList<>());
            response.setStatus(Constanta.NO_DATA_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/addBreed")
    public ResponseEntity<BreedDto> addBreed(@RequestBody final BreedDto breedDto){
        Breed breed =  breedService.addBreed(breedDto);
        return new ResponseEntity<>(BreedDto.from(breed), HttpStatus.OK);
    }

    @DeleteMapping(value = "{breedName}")
    public ResponseEntity<Response> deleteCBreed(@PathVariable final String breedName){
        Breed breed = breedService.deleteBreed(breedName);
        Response response = new Response();

        if(breed!=null){
            response.setMessagge(Constanta.DELETE_SUCCES);
            response.setStatus(Constanta.SUCCESS_STATUS);
        }else{
            response.setMessagge("");
            response.setStatus(Constanta.NO_DATA_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "{existingBreedName}/{newBreedName}")
    public ResponseEntity<BreedDto> editBreed(@PathVariable final String existingBreedName,
                                              @PathVariable final String newBreedName){
        Breed breed = breedService.editBreed(existingBreedName, newBreedName);
        return new ResponseEntity<>(BreedDto.from(breed), HttpStatus.OK);
    }

}
