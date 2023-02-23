package com.example.demo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.ImageDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Images;
import com.example.demo.model.Login;
import com.example.demo.model.UserData;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	HttpSession session;

	@Autowired
	ImageDao imageDao;

	public String register(UserData user) {
		if (user != null) {
			userDao.save(user);
			return "registred successfully";
		} else {
			return "please fill all the fields";
		}
	}

	public String loginService(Login login) {

		UserData userName = userDao.findByUserName(login.getUserName());
		if (login.getPassword().equals(userName.getPassword())) {
			session.setAttribute("userName", userName.getUserName());
			return "logged in succesfully";
		} else {
			return "invalid credentails";
		}
	}

	public ResponseEntity<?> saveImage(MultipartFile file) throws IOException {
		String userName = session.getAttribute("userName").toString();
		if (file != null && userName != null) {
			
			UserData userData = userDao.findByUserName(userName);
			List<Images> imgList=userData.getImages();		
			Images images =new Images();
			images.setImg(file.getBytes());
			imageDao.save(images);
			imgList.add(images);
			userData.setImages(imgList);
			userDao.save(userData);
			return new ResponseEntity<>("uploaded succesfully", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("upload failed", HttpStatus.BAD_REQUEST);
	}

	public String logout() {
		session.invalidate();
		return "logged out successfully";
	}

	public ResponseEntity<byte[]> viewImageById(int imageId) {

		byte[] imageBytes = null;

		String userName = session.getAttribute("userName").toString();

		Images images =imageDao.findById(imageId).get();
		
		imageBytes = images.getImg();

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}

	public String deleteImage(int imageId) {
		String userName = session.getAttribute("userName").toString();
		UserData userData = userDao.findByUserName(userName);
		if (userData != null) {
			Images images = imageDao.findById(imageId).get();
			
			List<Images> imagesList = new ArrayList<>();
			imagesList.add(images);
			imagesList.remove(images);
			userData.setImages(imagesList);
			userDao.save(userData);
			imageDao.delete(images);
			return "deleted";
		} else {
			return "not deleted";
		}
	}

	public UserData myProfile() {
		String userName = session.getAttribute("userName").toString();
		UserData userData = userDao.findByUserName(userName);
		return userData;
	}

	public UserData viewAllImages() {
		String userName = session.getAttribute("userName").toString();
		UserData userData = userDao.findByUserName(userName);
		return userData;
	}
}
