package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.UserDao;
import com.example.demo.model.Login;
import com.example.demo.model.UserData;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@PostMapping("/register")
	public String register(@RequestBody UserData user) {
		return userService.register(user);
	}
	
	@GetMapping("/myProfile/{id}")
	public UserData user(@PathVariable int id) {
		return userDao.findByUserId(id);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Login login) {
		return userService.loginService(login);
	}
	
	@PostMapping("/saveImage")
	 public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		return userService.saveImage(file);
	}
	
	@PostMapping("/logout")
	 public String logout()  {
		return userService.logout();
	}
	
	@GetMapping("/viewImage")
	public ResponseEntity<byte[]> viewImage() throws IOException {
		return userService.viewImage();
	}
	
	@PostMapping("/deleteImage")
	public ResponseEntity<byte[]> deleteImage() throws IOException {
		return userService.deleteImage();
	}
	
}
