package com.Rakesh.blog.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rakesh.blog.entities.Category;
import com.Rakesh.blog.entities.Post;
import com.Rakesh.blog.entities.User;
import com.Rakesh.blog.exceptions.ResourceNotFoundException;
import com.Rakesh.blog.payloads.PostDto;
import com.Rakesh.blog.payloads.UserDto;
import com.Rakesh.blog.respositories.CategoryRepo;
import com.Rakesh.blog.respositories.PostRepo;
import com.Rakesh.blog.respositories.UserRepo;
import com.Rakesh.blog.services.PostService;

@Service
public class PostServiceImplementation implements PostService {

	
	@Autowired
	private PostRepo postRepo;
	 
	 @Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		
		
		
	Post post=this.modelMapper.map(postDto,Post.class);
	//User user=this.modelMapper.map(userDto,User.class)
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post updated=this.postRepo.save(post);
		return this.modelMapper.map(updated, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		
		//save the updated post
		Post updatedPost=this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		
    this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
		// TODO Auto-generated method stub
		//get the list of all Post
		 List<Post> posts= this.postRepo.findAll();
			
			//List<User>users=this.userRepo.findAll();
			List<PostDto>userDtos= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
			return userDtos;
		//return
	}

	//return the post by id
	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," post id",postId));
		return this.modelMapper.map(post,PostDto.class);
	
	}

	//Returning the post by category
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		List<Post>posts= this.postRepo.findByCategory(cat);
		List<PostDto>postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return  postDtos;
	}
   
	//return the posts by userId
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		//get the user
		User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
		
		
		//get the post by user
		List<Post>posts= this.postRepo.findByUser(user);
		
		
		//convert the post to PostDto
		List<PostDto>postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

}