package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class UserData {
	
	@Id
	@GeneratedValue
	private int userId;
	private String userName;
	private String password;
	private String gender;
	private String firstName;
	private String lastName;
	private String email;
	private String dateOfBirth;
	
	@OneToMany(fetch = FetchType.EAGER)
	List<Images> images;
	
}
