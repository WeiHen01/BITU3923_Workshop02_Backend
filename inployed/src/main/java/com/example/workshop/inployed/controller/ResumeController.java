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

import com.example.workshop.inployed.model.Resume;
import com.example.workshop.inployed.model.User;
import com.example.workshop.inployed.repository.ResumeRepository;
import com.example.workshop.inployed.repository.UserRepository;

@RestController
@RequestMapping("/resume")
public class ResumeController {

	@Autowired
	private ResumeRepository resumeRepos;
	
	@Autowired
	private UserRepository userRepos;
	
	@GetMapping
	public List<Resume> getAllResumes(){
		return resumeRepos.findAll();
	}
	
	@GetMapping("/{user}")
	public Resume getUserResume(@PathVariable int user) {
		return resumeRepos.retrieveAUserResume(user);
	}
	
	@PostMapping("/newResume/{userId}")
	public Resume createResume(@PathVariable int userId, 
			@RequestBody Resume request) {
	    User user = userRepos.findById(userId).orElseThrow(() 
	    		-> new RuntimeException("User not found"));
	    Resume resume = new Resume();
	    resume.setUserId(user);
	    resume.setEducationLvl(request.getEducationLvl());
	    resume.setMajor(request.getMajor());
	    return resumeRepos.save(resume);
	}
	
	
	/**
	 * Implementation for user update module
	 * @param user
	 * @return
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<?> UpdateUser(@PathVariable Integer id, 
			@RequestBody Resume updatedResume) {
		try {
            Resume currentResume = resumeRepos.getById(id);

            if (currentResume == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            // Update fields if provided
            if (updatedResume.getEducationLvl() != null) {
            	currentResume.setEducationLvl(updatedResume.getEducationLvl());
            }

            if (updatedResume.getMajor() != null) {
            	currentResume.setMajor(updatedResume.getMajor());
            }

            resumeRepos.save(currentResume);
            
            // return HTTP status response code of 200 means OK
            return new ResponseEntity<>("Resume details updated successfully", HttpStatus.OK);
         
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>("Error updating resume details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
}
