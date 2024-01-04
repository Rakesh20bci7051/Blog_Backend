package com.Rakesh.blog.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Rakesh.blog.exceptions.*;
import com.Rakesh.blog.entities.*;
import com.Rakesh.blog.payloads.UserDto;
import com.Rakesh.blog.respositories.*;
import com.Rakesh.blog.services.UserService;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
      User obj=this.dtoToUser(user);
      User saved=this.userRepo.save(obj);
      return this.userToDto(saved);
      
    //  return obj;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
	    User updatedUser=this.userRepo.save(user);//updated user
	
         UserDto userDto1=	this.userToDto(updatedUser);// new updated userDto
         
          return userDto1;//return the Updated UserDto
		
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User>users=this.userRepo.findAll();
		List<UserDto>userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
		
this.userRepo.delete(user);
	}
//changing UserDto to User
	private User dtoToUser(UserDto userDto)
	{
		User user=this.modelMapper.map(userDto,User.class); //convert directly
		/*user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());*/
		return user;
	}


	public UserDto userToDto(User user)
	{
		UserDto obj=this.modelMapper.map(user, UserDto.class);
		/*obj.setId(user.getId());
		obj.setEmail(user.getEmail());
		obj.setName(user.getName());
		obj.setAbout(user.getAbout());
		obj.setPassword(user.getPassword());*/
		
		return obj;
	}

	
	//register new user
	@Override
	public UserDto registerNewUser(UserDto user) {
		// TODO Auto-generated method stub
		User obj=this.modelMapper.map(user, User.class);
		obj.setPassword(this.passwordEncoder.encode(obj.getPassword()));
		
		User newUser=this.userRepo.save(obj);
		return this.modelMapper.map(newUser, UserDto.class);
		
		
		
	}
}
