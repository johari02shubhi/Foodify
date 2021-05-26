package com.online.foodify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.online.foodify.service.CategoryService;
import com.online.foodify.service.FoodProductService;

@Controller
public class UserHomeController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	FoodProductService foodProductService;
	
	@GetMapping({"/","/home"})
	public String userHome(Model model) {
		return "index";
	}
	
	@GetMapping("/foods")
	public String foods(Model model) {
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("foodProducts",foodProductService.getAllFoodProduct());
		return "foods";
	}
}
