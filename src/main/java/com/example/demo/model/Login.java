package com.example.demo.model;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class Login {
	private String userName;
	private String password;
}
