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

import com.Rakesh.blog.entities.Post;
import com.Rakesh.blog.payloads.ApiResponse;
import com.Rakesh.blog.payloads.PostDto;
import com.Rakesh.blog.services.PostService;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = { "*" })
public class PostController {

	
	@Autowired
	private PostService postService;
	 //create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
	{
		List<PostDto>posts=this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	//getBy Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto>posts=this.postService.getPostsByUser(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get all the post
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>>getAllposts()
	{
		 return ResponseEntity.ok(this.postService.getAllPost());
	}
	
	//get post by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity getPostById(@PathVariable Integer postId)
	{
		return ResponseEntity.ok(this.postService.getPostById(postId));
	}
	
	//delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId)
	{
		
		this.postService.deletePost(postId);
		 return new ApiResponse("Post deleted success",true);
	}
	
	//update post
		@PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto> UpdatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
		{
			
			PostDto updatePost=this.postService.updatePost(postDto,postId);
			 return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		}
		
	
}
