package com.Rakesh.blog.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rakesh.blog.entities.Category;
import com.Rakesh.blog.entities.Post;
import com.Rakesh.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {

	List<Post>findByUser(User userId);
	List<Post>findByCategory(Category category);
	
}
