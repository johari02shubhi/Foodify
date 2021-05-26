package com.online.foodify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.online.foodify.service.CategoryService;
import com.online.foodify.service.FoodProductService;

@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	FoodProductService foodProductService;
	
	@GetMapping({"/", "/home"})
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", foodProductService.getAllFoodProduct());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", foodProductService.getAllFoodByCategoryId(id));
		return "shop";
	}
	
	
	
	 @GetMapping("/shop/viewproduct/{id}") 
	 public String viewProduct(Model model, @PathVariable int id) {
		 model.addAttribute("categories", categoryService.getAllCategory());
		 model.addAttribute("product", foodProductService.getFoodById(id).get()); 
		 return "viewProduct";
		}
	 

}
