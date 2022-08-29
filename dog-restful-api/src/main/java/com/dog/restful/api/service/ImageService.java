package com.dog.restful.api.service;

import com.dog.restful.api.model.Image;
import com.dog.restful.api.model.SubBreed;
import com.dog.restful.api.model.exception.ItemNotFoundException;
import com.dog.restful.api.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image addImage(Image image) {
        return imageRepository.save(image);
    }

    public String getRandomImage() {
        String urlImage = imageRepository.findRandomImage();

        return urlImage;
    }

    public List<String> getImageByBreed(String breedName) {
        List<String> imageUrls = new ArrayList<>();
        imageUrls = imageRepository.findImagegByBreed(breedName);
        return imageUrls;
    }

}
