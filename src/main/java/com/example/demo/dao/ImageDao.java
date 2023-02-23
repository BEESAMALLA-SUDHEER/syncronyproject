package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Images;

public interface ImageDao extends JpaRepository<Images, Integer>{

}
