package com.example.workshop.inployed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@GetMapping("/{id}")
	public Forum getCertainForumbyID(@PathVariable int id) {
		return forumRepos.findByForumId(id);
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
