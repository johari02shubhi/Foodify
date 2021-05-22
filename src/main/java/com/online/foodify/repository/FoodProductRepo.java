package com.online.foodify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.foodify.model.FoodProduct;

public interface FoodProductRepo extends JpaRepository<FoodProduct, Long>{
	List<FoodProduct> findAllByCategory_Id(int Id);
}
