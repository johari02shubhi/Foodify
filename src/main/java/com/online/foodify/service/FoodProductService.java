package com.online.foodify.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.foodify.model.FoodProduct;
import com.online.foodify.repository.FoodProductRepo;
@Service
public class FoodProductService {
	@Autowired 
	FoodProductRepo foodProductRepo; 
	
	public List<FoodProduct> getAllFoodProduct(){
		return foodProductRepo.findAll();
	}
	public void addFoodProduct(FoodProduct foodProduct) {
		foodProductRepo.save(foodProduct);
	}
	public void removeFoodbyId(long id) {
		foodProductRepo.deleteById(id);
	}

	public Optional<FoodProduct> updateFoodById(long id) {
		return foodProductRepo.findById(id);
	}
	public List<FoodProduct> getAllFoodByCategoryId(int id){
		return foodProductRepo.findAllByCategory_Id(id);
	}
}
