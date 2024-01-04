package com.Rakesh.blog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comments")
@Getter
@Setter
public class Comment {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String  content;
	
	@JsonBackReference(value="post-movement")
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;
	
}
