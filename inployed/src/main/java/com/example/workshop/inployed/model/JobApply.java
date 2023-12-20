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
@Table(name="job_apply")
public class JobApply{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "ApplyID")
    private int id;
 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ManyToOne
	@JoinColumn (name = "UserID", referencedColumnName = "UserID")
	private User UserID;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ManyToOne
	@JoinColumn (name = "AdsID", referencedColumnName = "AdsID")
	private Job adsId;
	
	@Column (name = "ApplyStartDate")
	private String applyStartDate;
	
	@Column (name = "ApplyEndDate")
	private String applyEndDate;
	
	@Column (name = "ApplyStartTime")
	private String applyStartTime;
	
	@Column (name = "ApplyEndTime")
	private String applyEndTime;
	
	@Column (name = "ApplyStatus")
	private String applyStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserID() {
		return UserID;
	}

	public void setUserID(User userID) {
		UserID = userID;
	}

	public Job getAdsId() {
		return adsId;
	}

	public void setAdsId(Job adsId) {
		this.adsId = adsId;
	}

	public String getApplyStartDate() {
		return applyStartDate;
	}

	public void setApplyStartDate(String applyStartDate) {
		this.applyStartDate = applyStartDate;
	}

	public String getApplyEndDate() {
		return applyEndDate;
	}

	public void setApplyEndDate(String applyEndDate) {
		this.applyEndDate = applyEndDate;
	}

	public String getApplyStartTime() {
		return applyStartTime;
	}

	public void setApplyStartTime(String applyStartTime) {
		this.applyStartTime = applyStartTime;
	}

	public String getApplyEndTime() {
		return applyEndTime;
	}

	public void setApplyEndTime(String applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	
}
