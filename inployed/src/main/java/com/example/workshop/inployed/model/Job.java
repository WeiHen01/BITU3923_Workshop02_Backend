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
@Table(name="job")
public class Job {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "AdsId")
	private int AdsId;
	
	@Column (name = "JobPosition")
	private String jobPosition;
	
	@Column (name = "JobDescription")
	private String jobDescription;
	
	@Column (name = "JobRemote")
	private String jobRemote;
	
	@Column (name = "JobDate")
	private String jobDate;
	
	@Column (name = "JobTime")
	private String jobTime;
	
	@Column (name = "AdsDate")
	private String AdsDate;
	
	@Column (name = "AdsTime")
	private String AdsTime;
	
	@Column (name = "JobCommitment")
	private String jobCommit;
	
	@Column (name = "Salary")
	private String salary;
	
	@Column (name = "IndustryField")
	private String industry;
	
	@ManyToOne
	@JoinColumn (name = "CompanyID")
	private Company companyId;

	public int getAdsId() {
		return AdsId;
	}

	public void setAdsId(int adsId) {
		AdsId = adsId;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobRemote() {
		return jobRemote;
	}

	public void setJobRemote(String jobRemote) {
		this.jobRemote = jobRemote;
	}

	public String getJobDate() {
		return jobDate;
	}

	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
	}

	public String getJobTime() {
		return jobTime;
	}

	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}

	public String getAdsDate() {
		return AdsDate;
	}

	public void setAdsDate(String adsDate) {
		AdsDate = adsDate;
	}

	public String getAdsTime() {
		return AdsTime;
	}

	public void setAdsTime(String adsTime) {
		AdsTime = adsTime;
	}

	public String getJobCommit() {
		return jobCommit;
	}

	public void setJobCommit(String jobCommit) {
		this.jobCommit = jobCommit;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Company getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Company companyId) {
		this.companyId = companyId;
	}

	
	
	

}

