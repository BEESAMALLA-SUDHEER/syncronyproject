package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
 @Data
public class Images {
	
	@Id
	@GeneratedValue
	private int imageId;
	
	@JsonIgnore
	@Lob
	private byte [] img;
}
