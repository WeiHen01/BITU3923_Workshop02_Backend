package com.example.workshop.inployed.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="forum_post")
public class ForumPost {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "PostID")
	private int postId;
	
	@ManyToOne
	@JoinColumn(name = "UserID")
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "ForumID")
	private Forum forumId;
	
	@Column (name = "PostTitle")
	private String postTitle;
	
	@Column (name = "PostDescription")
	private String postDesc;
	
	@Column(name = "PostDate")
	private String postDate;
	
	@Column(name = "PostTime")
	private String postTime;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Forum getForumId() {
		return forumId;
	}

	public void setForumId(Forum forumId) {
		this.forumId = forumId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostDesc() {
		return postDesc;
	}

	public void setPostDesc(String postDesc) {
		this.postDesc = postDesc;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	} 

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	
}
