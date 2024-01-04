package com.Rakesh.blog.payloads;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min=4,message="Name should be less than 4 characters")
	private String name;
	
	@Email(message= "Invalid Email")
	private String email;
	
	@NotEmpty(message="Password should not be null")
	@Size(min =3,max=10,message=" Min char=3 and max=10 for password should not be there")
	private String password;
	
	private String about;
	
	@JsonIgnore
	public String getPassword()
	{
		return this.password;
	}
	@JsonProperty
	public void setPassword(String password)
	{
		this.password=password;
	}
	
}
