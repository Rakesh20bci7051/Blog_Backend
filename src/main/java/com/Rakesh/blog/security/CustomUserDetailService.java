package com.Rakesh.blog.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Rakesh.blog.entities.User;
import com.Rakesh.blog.exceptions.ResourceNotFoundException;
import com.Rakesh.blog.respositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    
   // private UserRepo userRepo;
    private final UserRepo userRepo;

    @Autowired
    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    //logger.info("Searching for user with email: {}", username);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	logger.info("Searching for user with email: {}", username);
    	logger.info(username);
    	
    	  //System.out.println("Received username: " + username);
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email:" + username, 0));

        // Log the email using SLF4J logger
        logger.info("Email is: {}", user.getEmail());

        // Return the UserDetails object (assuming User implements UserDetails)
        return user;
    }
}
