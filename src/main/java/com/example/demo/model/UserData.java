package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
	@Lob
	private byte [] images;
	
}
