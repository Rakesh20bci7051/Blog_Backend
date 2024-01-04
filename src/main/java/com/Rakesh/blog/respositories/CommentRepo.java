package com.Rakesh.blog.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Rakesh.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
	

}
