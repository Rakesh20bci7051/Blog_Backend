package com.Rakesh.blog.security;

import com.Rakesh.blog.payloads.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

	private String jwtToken;
	private String username;
	private UserDto user;
}
