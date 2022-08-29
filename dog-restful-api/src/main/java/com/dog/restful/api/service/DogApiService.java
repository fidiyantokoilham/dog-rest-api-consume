package com.dog.restful.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.dog.restful.api.model.dto.BreedDto;
import com.dog.restful.api.model.dto.ImageDto;
import com.dog.restful.api.model.dto.SubBreedDto;

@Service
public class DogApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BreedService breedService;


    public String parse(String url) {
        try {
            List<BreedDto> listBreed = new ArrayList<>();
            String s = restTemplate.getForObject(url, String.class);
            Object obj = JSONValue.parse(s);
            JSONObject js = (JSONObject) obj;

            HashMap<String, ArrayList<String>> breedsWithSubBreed = (HashMap<String, ArrayList<String>>) js.get("message");

            String status = (String) js.get("status");

            for (Entry<String, ArrayList<String>> entry : breedsWithSubBreed.entrySet()) {
                BreedDto breedsDto = new BreedDto();
                ImageDto imageDto = new ImageDto();
                List<SubBreedDto> subBreedList = new ArrayList<>();
                List<ImageDto> imageList = new ArrayList<>();

                SubBreedDto subBreedDto = new SubBreedDto();

                //Get Image deom REST
                ArrayList<String> images = getImageJson(entry.getKey());

                // Condition fo dog breed is "sheepdog"
                if (entry.getKey().equalsIgnoreCase("sheepdog")) {

                    for (String subBreedValue : entry.getValue()) {

                        breedsDto = new BreedDto();
                        breedsDto.setBreadName(entry.getKey() + "-" + subBreedValue);
                        breedsDto.setSubBreed(new ArrayList<>());
                        breedsDto.setImage(new ArrayList<>());

                        listBreed.add(breedsDto);

                    }
                    continue;
                } else if (entry.getKey().equalsIgnoreCase("terrier")) {
                    for (String subBreedValue : entry.getValue()) {
                        imageList = new ArrayList<>();
                        for (String getImage : images) {
                            if (getImage.contains(subBreedValue)) {
                                imageDto = new ImageDto();
                                imageDto.setUrl(getImage);
                                imageList.add(imageDto);
                            }
                        }

                        breedsDto = new BreedDto();
                        breedsDto.setBreadName(entry.getKey() + "-" + subBreedValue);
                        breedsDto.setSubBreed(new ArrayList<>());
                        breedsDto.setImage(imageList);

                        listBreed.add(breedsDto);
                    }
                    continue;
                } else {
                    for (String getImage : images) {
                        imageDto = new ImageDto();
                        imageDto.setUrl(getImage);
                        imageList.add(imageDto);
                    }
                    for (String subBreedValue : entry.getValue()) {
                        subBreedDto = new SubBreedDto();
                        subBreedDto.setSubBreedName(subBreedValue);

                        subBreedList.add(subBreedDto);
                    }
                    breedsDto.setBreadName(entry.getKey());
                    breedsDto.setSubBreed(subBreedList);
                    breedsDto.setImage(imageList);
                    listBreed.add(breedsDto);
                }


            }

            for (BreedDto breedsDto : listBreed) {
                breedService.addBreedFromRest(breedsDto);
            }

            return "Upload Success !!";
        } catch (Exception a) {
            return a.getMessage();
        }

    }

    public ArrayList<String> getImageJson(String breed) {
        String url = "https://dog.ceo/api/breed/" + breed + "/images";
        String jsonValue = restTemplate.getForObject(url, String.class);
        Object obj = JSONValue.parse(jsonValue);
        JSONObject js = (JSONObject) obj;
        ArrayList<String> breedsWithSubBreed = (ArrayList<String>) js.get("message");
        return breedsWithSubBreed;
    }

}
