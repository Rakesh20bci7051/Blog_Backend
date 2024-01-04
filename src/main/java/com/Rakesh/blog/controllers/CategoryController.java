package com.Rakesh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Rakesh.blog.payloads.ApiResponse;
import com.Rakesh.blog.payloads.CategoryDto;
import com.Rakesh.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = { "*" })
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto obj=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(obj,HttpStatus.CREATED);
	}
	//update
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId)
	{
		CategoryDto obj=this.categoryService.updateCategory(categoryDto,catId);
		return new ResponseEntity<CategoryDto>(obj,HttpStatus.OK);
	}
	
	//delete
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse>deleteCategory(@PathVariable Integer catId)
	{
		this.categoryService.deleteCategory(catId);
		//return new ResponseEntity<CategoryDto>(obj,HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is delete successfully",true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer catId)
	{
		 return ResponseEntity.ok(this.categoryService.getCategory(catId));
	}
	//get All
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getCategories()
	{
  List<CategoryDto> list=this.categoryService.getCategories();
  
  return ResponseEntity.ok(list);
	}
	
	
	
	
}
