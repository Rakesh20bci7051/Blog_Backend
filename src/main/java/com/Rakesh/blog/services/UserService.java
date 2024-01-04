package com.Rakesh.blog.services;
import java.util.List;
import com.Rakesh.blog.payloads.UserDto;

public interface UserService {
   
	
	//register a new user
	UserDto registerNewUser(UserDto user);
	 UserDto createUser(UserDto user);
	 UserDto updateUser(UserDto user,Integer userId);
	 UserDto getUserById(Integer userId);
	 List<UserDto>getAllUsers();
	 void deleteUser(Integer userId);
}
