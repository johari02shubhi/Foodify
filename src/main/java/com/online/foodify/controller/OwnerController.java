package com.online.foodify.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.online.foodify.model.Category;
import com.online.foodify.service.CategoryService;

@Controller
public class OwnerController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/owner")
	public String ownerHome() {
		return "restaurantOwnerHome";
	}
	@GetMapping("/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	@GetMapping("/add")
	public String getaddCategories(Model model) {
		model.addAttribute("category", new Category());
		return "addCategories";
	}
	@PostMapping("/add")
	public String postaddCategories(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/categories";
	}
	@GetMapping("/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryService.removeCategorybyId(id);
		return "redirect:/categories";
	}
	@GetMapping("/categories/update/{id}")
	public String updateCategoryById(@PathVariable int id,Model model) {
		Optional<Category> category=categoryService.updateCategoryById(id);
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "addCategories";
		}else {
			return "404";
		}
	}
}
