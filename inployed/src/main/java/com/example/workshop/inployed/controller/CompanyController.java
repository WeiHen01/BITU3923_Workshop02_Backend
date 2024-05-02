package com.example.workshop.inployed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshop.inployed.model.Company;
import com.example.workshop.inployed.repository.CompanyRepository;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepos;
	
	@GetMapping
	public List<Company> getCompany(){
		return companyRepos.findAll();
	}
	
	@GetMapping("/{userCompany}")
	public Company getCertainCompanyInfo(@PathVariable int userCompany){
		return companyRepos.findByCompanyId(userCompany);
	}
	
	@PostMapping("/addCompany")
	public Company insertCompany(@RequestBody Company company) {
		return companyRepos.save(company);
	}
	
	/**
	 * Implementation for user update module
	 * @param user
	 * @return
	 */
	@PutMapping("/update/company/{id}")
	public ResponseEntity<?> UpdateJobPost(@PathVariable int id, @RequestBody Company updatedCompany) {
		try {
            Company existingCompany = companyRepos.findByCompanyId(id);

            if (existingCompany == null) {
                return new ResponseEntity<>("This Company is not found", HttpStatus.NOT_FOUND);
            }

            if (updatedCompany.getCompanyName() != null) {
            	existingCompany.setCompanyName(updatedCompany.getCompanyName());
            }
            
            if (updatedCompany.getCompanyEmail()!= null) {
            	existingCompany.setCompanyEmail(updatedCompany.getCompanyEmail());
            }
            
            if (updatedCompany.getCompanyContact() != null) {
            	existingCompany.setCompanyContact(updatedCompany.getCompanyContact());
            }
            
            if (updatedCompany.getCompanyCity() != null) {
            	existingCompany.setCompanyCity(updatedCompany.getCompanyCity());
            }
            
            if (updatedCompany.getCompanyState() != null) {
            	existingCompany.setCompanyState(updatedCompany.getCompanyState());
            }
            
            if (updatedCompany.getCompanyCountry() != null) {
            	existingCompany.setCompanyCountry(updatedCompany.getCompanyCountry());
            }
            
            companyRepos.save(existingCompany);
            
            // return HTTP status response code of 200 means OK
            return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
         
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>("Error updating company", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}
