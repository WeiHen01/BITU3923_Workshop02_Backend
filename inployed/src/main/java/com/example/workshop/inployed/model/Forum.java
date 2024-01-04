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
@Table(name="forum")
public class Forum {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "ForumID")
	private int forumId;
	
	@Column (name = "ForumName")
	private String forumname;
	
	@Column (name = "ForumDescription")
	private String forumDesc;
	
	@Column(name = "ForumDate")
	private String forumDate;
	
	@Column(name = "ForumTime")
	private String forumTime;
	
	@ManyToOne
	@JoinColumn(name = "AdminID")
	private User adminID;

	/**
	 * Getters and setters
	 * @return
	 */
	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public String getForumname() {
		return forumname;
	}

	public void setForumname(String forumname) {
		this.forumname = forumname;
	}

	public String getForumDesc() {
		return forumDesc;
	}

	public void setForumDesc(String forumDesc) {
		this.forumDesc = forumDesc;
	}

	public String getForumDate() {
		return forumDate;
	}

	public void setForumDate(String forumDate) {
		this.forumDate = forumDate;
	}

	public String getForumTime() {
		return forumTime;
	}

	public void setForumTime(String forumTime) {
		this.forumTime = forumTime;
	}

	public User getAdminID() {
		return adminID;
	}

	public void setAdminID(User adminID) {
		this.adminID = adminID;
	}

}
