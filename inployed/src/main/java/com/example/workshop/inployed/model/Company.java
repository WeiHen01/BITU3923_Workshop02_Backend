package com.example.workshop.inployed.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="company")
public class Company {

	/**
	 * Attributes
	 */
	
	/**
	 * The primary key for User class
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "CompanyID")
	private int companyID;
	
	@Column (name = "CompanyName")
	private String companyName;
	
	@Column (name = "CompanyCity")
	private String companyCity;
	
	@Column (name = "CompanyState")
	private String companyState;
	
	@Column (name = "CompanyCountry")
	private String companyCountry;
	
	@Column (name = "CompanyEmail")
	private String companyEmail;
	
	@Column (name = "CompanyContact")
	private String companyContact;
	
	@Column (name = "CompanyStatus")
	private String companyStatus;

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}

	public String getCompanyState() {
		return companyState;
	}

	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}

	public String getCompanyCountry() {
		return companyCountry;
	}

	public void setCompanyCountry(String companyCountry) {
		this.companyCountry = companyCountry;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyContact() {
		return companyContact;
	}

	public void setCompanyContact(String companyContact) {
		this.companyContact = companyContact;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}
	
}
