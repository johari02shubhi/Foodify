package com.online.foodify.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.online.foodify.dto.FoodProductDto;
import com.online.foodify.model.FoodProduct;
import com.online.foodify.service.CategoryService;
import com.online.foodify.service.FoodProductService;

@Controller
public class FoodProductController {
	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productimages";
	@Autowired
	FoodProductService foodProductService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/foodProducts")
	public String foodProducts(Model model) {
		model.addAttribute("foodProducts",foodProductService.getAllFoodProduct());
		return "foodList";
	}
	@GetMapping("/addFood")
	public String getAddFood(Model model) {
		model.addAttribute("productDTO", new FoodProductDto());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "addFood";
	}
	@PostMapping("/addFood")
	public String postAddFood(@ModelAttribute("productDTO") FoodProductDto foodProductDto,
								@RequestParam("foodImage") MultipartFile file,
								@RequestParam("imgName")String imgName)throws IOException {
		FoodProduct foodProduct=new FoodProduct();
		foodProduct.setId(foodProductDto.getId());
		foodProduct.setName(foodProductDto.getName());
		foodProduct.setCategory(categoryService.updateCategoryById(foodProductDto.getCategoryId()).get());
		foodProduct.setPrice(foodProductDto.getPrice());
		foodProduct.setDescription(foodProductDto.getDescription());
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID=file.getOriginalFilename();
			Path fileNameAndPath=Paths.get(uploadDir, imageUUID);//File Path and Name
			Files.write(fileNameAndPath, file.getBytes());//Actual file
		}else {
			imageUUID=imgName;
		}
		foodProduct.setImageName(imageUUID);
		foodProductService.addFoodProduct(foodProduct);
		return "redirect:/foodProducts";
	}
	@GetMapping("/foodProducts/delete/{id}")
	public String deleteFood(@PathVariable long id) {
		foodProductService.removeFoodbyId(id);
		return "redirect:/foodProducts";
	}
	@GetMapping("/foodProducts/update/{id}")
	public String updateFoodById(@PathVariable long id,Model model) {
		Optional<FoodProduct> foodProduct=foodProductService.updateFoodById(id);
		if(foodProduct.isPresent()) {
			model.addAttribute("foodProduct", foodProduct.get());
			return "addFood";
		}else {
			return "404";
		}
	}
}
