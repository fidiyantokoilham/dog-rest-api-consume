package com.dog.restful.api.model.dto;

import com.dog.restful.api.model.SubBreed;
import lombok.Data;

@Data
public class SubBreedDto {

    private Long subNameId;
    private String subBreedName;

    public static SubBreedDto from(SubBreed subBreed){
        SubBreedDto itemDto = new SubBreedDto();
        itemDto.setSubNameId(subBreed.getId());
        itemDto.setSubBreedName(subBreed.getSubBreedName());
        return itemDto;
    }
}
