package com.example.workshop.inployed.controller;

import java.util.List;

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

import com.example.workshop.inployed.model.Job;
import com.example.workshop.inployed.repository.JobRepository;

@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
    private JobRepository adsRepos;
	
	@GetMapping
	public List<Job> getAds(){
		return adsRepos.findAll();
	}
	
	@GetMapping("/{id}")
	public Job getAdsById(@PathVariable int id) {
		return adsRepos.findByJobId(id);
	}
	
	@GetMapping("/search/{name}")
	public List<Job> getAdsByName(@PathVariable String name) {
		return adsRepos.findJobByName(name);
	}
	
	@PostMapping("/addJobPost")
	public Job addJob(@RequestBody Job jobPost){
		return adsRepos.save(jobPost);
	}
	
	@GetMapping("/company/ownPost/{company}")
	public List<Job> getCompanyOwnJobs(@PathVariable int company){
		return adsRepos.findCompanyOwnJob(company);
	}
	
	/**
	 * Implementation for user update module
	 * @param user
	 * @return
	 */
	@PutMapping("/update/jobPost/{id}")
	public ResponseEntity<?> UpdateJobPost(@PathVariable int id, @RequestBody Job updatedJob) {
		try {
            Job existingJob = adsRepos.findByJobId(id);

            if (existingJob == null) {
                return new ResponseEntity<>("This Job is not found", HttpStatus.NOT_FOUND);
            }

            if (updatedJob.getJobPosition() != null) {
            	existingJob.setJobPosition(updatedJob.getJobPosition());
            }
            
            if (updatedJob.getJobDescription()!= null) {
            	existingJob.setJobDescription(updatedJob.getJobDescription());
            }
            
            if (updatedJob.getJobCommit() != null) {
            	existingJob.setJobCommit(updatedJob.getJobCommit());
            }
            
            if (updatedJob.getJobRemote() != null) {
            	existingJob.setJobRemote(updatedJob.getJobRemote());
            }
            
            if (updatedJob.getJobTime() != null) {
            	existingJob.setJobTime(updatedJob.getJobTime());
            }
            
            if (updatedJob.getJobDate() != null) {
            	existingJob.setJobDate(updatedJob.getJobDate());
            }
            
            if (updatedJob.getSalary() != null) {
            	existingJob.setSalary(updatedJob.getSalary());
            }
            
            if (updatedJob.getIndustry() != null) {
            	existingJob.setIndustry(updatedJob.getIndustry());
            }
            
            
            adsRepos.save(existingJob);
            
            // return HTTP status response code of 200 means OK
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
         
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>("Error updating job", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	
	@DeleteMapping("/company/delete/ownPost/{post}")
	public ResponseEntity<HttpStatus> deleteCompanyOwnJobs(@PathVariable long post)
	{
		adsRepos.deleteById(post);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
