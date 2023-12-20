package com.example.workshop.inployed.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshop.inployed.model.JobApply;
import com.example.workshop.inployed.repository.JobApplyRepository;

@RestController
@RequestMapping("/jobapply")
public class JobApplyController {

	
	@Autowired
    private JobApplyRepository jobapplyRepos;
	
	@GetMapping
	public List<JobApply> getAds(){
		return jobapplyRepos.findAll();
	}
	
	@PostMapping("/addApply")
	public JobApply addJobApplyRequest(@RequestBody JobApply apply){
		return jobapplyRepos.save(apply);
	}
	
	@GetMapping("/{id}")
	public List<JobApply> getJobApplybyId(@PathVariable Integer id) {
		return jobapplyRepos.ViewJobAppliedByUser(id);
	}
	
	@GetMapping("/applyStatus/{id}")
	public List<JobApply> getJobStatusByJob(@PathVariable Integer id) {
		return jobapplyRepos.ViewJobApplyStatus(id);
	}
	
	@GetMapping("/companyView/jobrequests/{companyId}")
	public List<JobApply> getJobRequests(@PathVariable Integer companyId){
		return jobapplyRepos.ViewJobRequestsList(companyId);
	}
	
	@PutMapping("/update/{id}/{applyStatus}")
	public ResponseEntity<?> updateStatus(@PathVariable Integer id, @PathVariable String applyStatus) {
		// Optional here
		// used to represent a value that may or may not be present
		// an Optional object can either contain a non-null value (considered present) 
		// or it can contain no value at all (considered empty)
		Optional<JobApply> jobrequest = jobapplyRepos.findById(id);
		
		//Default retrieved user is null / empty record
		JobApply job = null;
		
		// if the result is found
		if (jobrequest.isPresent()) {
			
			// obtain the user information
			job = jobrequest.get();
			
			// update the user status
			job.setApplyStatus(applyStatus);
			jobapplyRepos.save(job);
			
			// return HTTP status response code of 200 means OK
			return ResponseEntity.ok().body(job);
		} 
		// else the result is not found
		else {
			// it will return HTTP status response code of 401
			// where 401 is unauthorized (can test at Postmann)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
					body("fail to update approval status");
		}
		
	}
	
	@DeleteMapping("/delete/ownApply/{apply}")
	public ResponseEntity<HttpStatus> deleteCompanyOwnJobs(@PathVariable int apply)
	{
		jobapplyRepos.deleteById(apply);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
