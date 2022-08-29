package com.dog.restful.api.model.dto;

import com.dog.restful.api.model.Image;
import lombok.Data;

@Data
public class ImageDto {
	private String url;
	private Long imageId;

	public static ImageDto from(Image image){
		ImageDto imageDto = new ImageDto();
		imageDto.setImageId(image.getId());
		imageDto.setUrl(image.getUrl());
		return imageDto;
	}
}
