package com.Rakesh.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postId;
    
    @Column(name="post_title",length=100,nullable=false)
	private String title;
    
    @Column(length=1000000000)
	private String content;
	
	
	private Date addedDate;
	
	@JsonBackReference(value="category-movement")
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JsonBackReference(value="user-movement")
	private  User user;
	
	@JsonManagedReference(value="comment-movement")
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Comment>comments=new HashSet<>();
}
