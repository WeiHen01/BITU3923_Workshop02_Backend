package com.example.workshop.inployed.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="resume")
public class Resume {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "ResumeID")
	private int ResumeId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "UserID", referencedColumnName = "UserID")
	private User userId;
	
	@Column (name = "EducationLevel")
	private String EducationLvl;
	
	@Column (name = "MajorCourse")
	private String Major;
	
	
	/**
	 * Getter and setter
	 * @return
	 */
	
	public int getResumeId() {
		return ResumeId;
	}

	public void setResumeId(int resumeId) {
		ResumeId = resumeId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getEducationLvl() {
		return EducationLvl;
	}

	public void setEducationLvl(String educationLvl) {
		EducationLvl = educationLvl;
	}

	public String getMajor() {
		return Major;
	}

	public void setMajor(String major) {
		Major = major;
	}
	
	

}
