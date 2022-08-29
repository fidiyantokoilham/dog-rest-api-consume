package com.dog.restful.api.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "IMAGE")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IMAGE_ID")
	private Long id;
	@Column(name = "URL")
	private String url;
	@Column(name = "FK_BREED_ID")
	private Long fkId;



}
