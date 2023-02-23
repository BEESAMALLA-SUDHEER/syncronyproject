package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserData;

public interface UserDao extends JpaRepository<UserData, Integer>{

	UserData findByUserId(int id);

	UserData findByUserName(String userName);
}
