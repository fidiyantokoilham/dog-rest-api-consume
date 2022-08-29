package com.dog.restful.api.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "BREED")
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BREED_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
    @OneToMany(targetEntity = SubBreed.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "FK_BREED_ID", referencedColumnName = "BREED_ID")
    private List<SubBreed> subBreeds = new ArrayList<>();

    @OneToMany(targetEntity = Image.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "FK_BREED_ID", referencedColumnName = "BREED_ID")
    private List<Image> images = new ArrayList<>();

    public void addSubBreed(SubBreed subBreed) {
        subBreeds.add(subBreed);
    }

    public void removeSubBreed(SubBreed subBreed) {
        subBreeds.remove(subBreed);
    }


}
