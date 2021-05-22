package com.online.foodify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.foodify.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
