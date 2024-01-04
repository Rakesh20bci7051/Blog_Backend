package com.Rakesh.blog.payloads;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.Rakesh.blog.entities.Category;
import com.Rakesh.blog.entities.Comment;
import com.Rakesh.blog.entities.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private int postId;
	
	private String title;
	
	private String content;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	

	private Set<Comment>comments=new HashSet<>();
	
}
