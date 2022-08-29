package com.dog.restful.api.model;

import com.dog.restful.api.model.dto.SubBreedDto;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SUB_BREED")
public class SubBreed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SUB_BREED_ID")
    private Long id;

    @Column(name = "FK_BREED_ID")
    private Long fkId;

    @Column(name = "NAME")
    private String subBreedName;

/*    @ManyToOne
    private Breed breed;*/

    public static SubBreed from(SubBreedDto subBreedDto){
        SubBreed subBreed = new SubBreed();
        subBreed.setSubBreedName(subBreedDto.getSubBreedName());
        return subBreed;
    }

}
