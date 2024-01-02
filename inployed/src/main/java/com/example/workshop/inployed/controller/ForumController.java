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

import com.example.workshop.inployed.model.Forum;
import com.example.workshop.inployed.repository.ForumRepository;


@RestController
@RequestMapping("/forum")
public class ForumController {

	@Autowired
	private ForumRepository forumRepos;
	
	/**
	 * Implementation for forum creation module
	 * @param user
	 * @return
	 */
	@PostMapping("/create")
	public Forum CreateForum(@RequestBody Forum forum) {
        return forumRepos.save(forum);
    }
	
	/**
	 * Implementation for forum creation module
	 * @param user
	 * @return
	 */
	@PutMapping("/update")
	public Forum UpdateForum(@RequestBody Forum forum) {
        return forumRepos.save(forum);
    }
	
	/**
	 * Implementation for forum creation module
	 * @param user
	 * @return
	 */
	@GetMapping
	public List<Forum> listForum() {
        return forumRepos.findAll();
    }
	
	@GetMapping("/admin/ctrlForumAdmin/{adminID}")
	public List<Forum> listForum(@PathVariable int adminID) {
        return forumRepos.findAllundercertainAdmin(adminID);
    }

	
	@GetMapping("/{id}")
	public Forum getCertainForumbyID(@PathVariable int id) {
		return forumRepos.findByForumId(id);
	}
	
	@DeleteMapping("/admin/delete/deleteforum/{id}")
	public ResponseEntity<HttpStatus> deleteAdminForum(@PathVariable long id)
	{
		forumRepos.deleteById((int) id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Implementation for user update module
	 * @param user
	 * @return
	 */
	@PutMapping("/update/Forum/{id}")
	public ResponseEntity<?> UpdateForum(@PathVariable int id, @RequestBody Forum updatedForum) {
		try {
            Forum existingForum = forumRepos.findByForumId(id);

            if (existingForum == null) {
                return new ResponseEntity<>("This forum is not found", HttpStatus.NOT_FOUND);
            }

            if (updatedForum.getForumname() != null) {
            	existingForum.setForumname(updatedForum.getForumname());
            }
            
            if (updatedForum.getForumDesc()!= null) {
            	existingForum.setForumDesc(updatedForum.getForumDesc());
            }
            
            forumRepos.save(existingForum);
            
            // return HTTP status response code of 200 means OK
            return new ResponseEntity<>("Forum updated successfully", HttpStatus.OK);
         
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>("Error updating forum", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	
	/**
	 * Implementation for forum creation module
	 * @param user
	 * @return
	 */
	/*
	 * @DeleteMapping("/delete/{id}") public ResponseEntity<HttpStatus>
	 * DeleteForum(@PathVariable Long id) {
	 * 
	 * forumRepos.deleteById(id); return new ResponseEntity<>(HttpStatus.OK); }
	 */
	
}
