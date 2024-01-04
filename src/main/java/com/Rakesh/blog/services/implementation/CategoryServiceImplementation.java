package com.Rakesh.blog.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rakesh.blog.entities.Category;
import com.Rakesh.blog.exceptions.ResourceNotFoundException;
import com.Rakesh.blog.payloads.CategoryDto;
import com.Rakesh.blog.respositories.CategoryRepo;
import com.Rakesh.blog.services.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {

	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	  
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category obj=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(obj, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category obj=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		obj.setCategoryTitle(categoryDto.getCategoryTitle());
		obj.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updated=this.categoryRepo.save(obj);
		return this.modelMapper.map(updated, CategoryDto.class);
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		// TODO Auto-generated method stub
		List<Category>categories=this.categoryRepo.findAll();
		List<CategoryDto>DtoList=categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return DtoList;
		
	}

}
