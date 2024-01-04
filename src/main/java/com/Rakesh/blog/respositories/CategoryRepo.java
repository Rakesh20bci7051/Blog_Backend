package com.Rakesh.blog.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rakesh.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
	

	
}
