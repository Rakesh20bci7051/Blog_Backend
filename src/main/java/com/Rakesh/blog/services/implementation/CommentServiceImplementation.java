package com.Rakesh.blog.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Rakesh.blog.entities.Comment;
import com.Rakesh.blog.entities.Post;
import com.Rakesh.blog.exceptions.ResourceNotFoundException;
import com.Rakesh.blog.payloads.CommentDto;
import com.Rakesh.blog.respositories.CommentRepo;
import com.Rakesh.blog.respositories.PostRepo;
import com.Rakesh.blog.services.CommentService;

@Service
public class CommentServiceImplementation implements CommentService {
    
	@Autowired
	private PostRepo postRepo;
	
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		
		//get the post
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		
		
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		
		//set the comment to post
		comment.setPost(post);
		
		Comment savedComment=this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentId));
		this.commentRepo.delete(com);

	}

}
