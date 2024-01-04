package com.Rakesh.blog.services;

import java.util.List;

import com.Rakesh.blog.entities.Post;
import com.Rakesh.blog.payloads.PostDto;

public interface PostService {

	//create post
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update 
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	
	//get All Post
	
	List<PostDto>getAllPost();
	
	//get single post
	
	PostDto getPostById(Integer postId);
	
	//get all posts by category
	
	List<PostDto>getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	
	List<PostDto>getPostsByUser(Integer userId);

	
	
	
	
}
