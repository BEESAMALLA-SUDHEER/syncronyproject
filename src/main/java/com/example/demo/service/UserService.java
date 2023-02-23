package com.example.demo.service;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.UserDao;
import com.example.demo.model.Login;
import com.example.demo.model.UserData;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	
	@Autowired
	UserDao userDao;
	
	@Autowired
	HttpSession session;
	
	public String register(UserData user) {
		if (user != null) {
			userDao.save(user);
			return "registred successfully";
		}else {
			return "please fill all the fields";
		}
	}

	public String loginService(Login login) {
		
		UserData userName=userDao.findByUserName(login.getUserName());
		if (login.getPassword().equals(userName.getPassword())) {
			session.setAttribute("userName", userName.getUserName());
			return "logged in succesfully";
		}else {
			return "invalid credentails";
		}	
	}

	public ResponseEntity<?> saveImage(MultipartFile file) throws IOException {
		String userName=session.getAttribute("userName").toString();
		if(file!=null&&userName!=null) {
			UserData  userData = userDao.findByUserName(userName);
			userData.setImages(file.getBytes());
			userDao.save(userData);
			return new ResponseEntity<>("uploaded succesfully",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("upload failed",HttpStatus.BAD_REQUEST);
	}

	public String logout() {
		session.invalidate();
		return "logged out successfully";
	}

	
	
	@GetMapping("database/{id}")
	public ResponseEntity<byte[]> viewImage() 
	         {

		
		byte[] imageBytes = null;
		
		String userName=session.getAttribute("userName").toString();
		
		UserData userData = userDao.findByUserName(userName);
		
		imageBytes=userData.getImages();
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}

	public ResponseEntity<byte[]> deleteImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
