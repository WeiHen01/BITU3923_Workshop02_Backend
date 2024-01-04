package com.example.workshop.inployed.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author Edgar
 * This is the User class
 * 
 */

@Entity
@Table(name="user")
public class User {

	/**
	 * Attributes
	 */
	
	/**
	 * The primary key for User class
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "UserID")
	private int userId;
	
	@Column (name = "UserName")
	private String username;
	
	@Column (name = "UserEmail")
	private String userEmail;
	
	//write only will prevent user password from being returned in request
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column (name = "UserPassword")
	private String userPassword;
	
	@Column (name = "UserPosition")
	private String userPosition;
	
	@Column (name = "UserType")
	private String userType;
	
	@Column (name = "LastAccessDate")
	private String AccessDate;
	
	@Column (name = "LastAccessTime")
	private String AccessTime;
	
	@Column (name = "LastUpdateDate")
	private String UpdateDate;
	
	@Column (name = "LastUpdateTime")
	private String UpdateTime;
	
	@Column (name = "UserStatus")
	private String userStatus;
	
	@Column (name = "AdminID")
	private String AdminID;
	
	@ManyToOne
	@JoinColumn (name = "CompanyID")
	private Company Company;
	

	/**
	 * Getters and setters
	 */
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserpassword() {
		return userPassword;
	}

	public void setUserpassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getAccessDate() {
		return AccessDate;
	}

	public void setAccessDate(String accessDate) {
		AccessDate = accessDate;
	}

	public String getAccessTime() {
		return AccessTime;
	}

	public void setAccessTime(String accessTime) {
		AccessTime = accessTime;
	}

	public String getUpdateDate() {
		return UpdateDate;
	}

	public void setUpdateDate(String updateDate) {
		UpdateDate = updateDate;
	}

	public String getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getAdminID() {
		return AdminID;
	}

	public void setAdminID(String adminID) {
		AdminID = adminID;
	}

	public Company getCompany() {
		return Company;
	}

	public void setCompany(Company company) {
		Company = company;
	}


}
