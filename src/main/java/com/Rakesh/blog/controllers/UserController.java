package com.Rakesh.blog.controllers;

import java.util.List;
import java.util.Map;

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
import com.Rakesh.blog.payloads.UserDto;
import com.Rakesh.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = { "*" })
public class UserController {
     
	@Autowired
	 private UserService userService;
	 
	//POST -create user
	 @PostMapping("/")
	 public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto)
	 {
		 UserDto createUserDto=this.userService.createUser(userDto);
		 return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	 }
	 
	
	//PUT  -update user
	 @PutMapping("/{userId}")
	 public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid)
	 {
		UserDto updatedUser= this.userService.updateUser(userDto,uid);
		return ResponseEntity.ok(updatedUser);
		 
	 }
	 
	
	 @DeleteMapping("/{userId}")
	public ResponseEntity<?>deleteUser(@PathVariable("userId")Integer uid)
	{
		
		 this.userService.deleteUser(uid);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("User  deleted success",true),HttpStatus.OK);
	}
	//GET - all user get
	 @GetMapping("/") //the URI is mentioned inside the mapping
	 public ResponseEntity<List<UserDto>>getAllUsers()
	 {
		 return ResponseEntity.ok(this.userService.getAllUsers());
	 }
	
	//GET -user get
		 @GetMapping("/{userId}") //the URI is mentioned inside the mapping
		 public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer userId)
		 {
			 return ResponseEntity.ok(this.userService.getUserById(userId));
		 }
}
