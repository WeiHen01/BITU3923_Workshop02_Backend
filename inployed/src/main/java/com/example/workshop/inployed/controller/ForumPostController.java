package com.example.workshop.inployed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshop.inployed.model.ForumPost;
import com.example.workshop.inployed.repository.ForumPostRepository;

@RestController
@RequestMapping("/post")
public class ForumPostController {

	@Autowired
	private ForumPostRepository postRepos;
	
	@GetMapping("/list/{forum}")
	public List<ForumPost> listPostByForum(@PathVariable int forum){
		return postRepos.findByForumId(forum);
	}
	
	@GetMapping("/{forum}/{user}")
	public List<ForumPost> listPostByForum(@PathVariable int forum, 
			@PathVariable int user){
		return postRepos.findForumPostbyUserId(forum, user);
	}
	
	@PostMapping("/addPost")
	public ForumPost CreateForum(@RequestBody ForumPost post) {
        return postRepos.save(post);
    }
	
	/**
	 * Newly added controller method for delete forum post based on forum ID
	 * Needed for delete all posts under the forum before delete delete the forum
	 * @param forum
	 * @return
	 */
	@Transactional
	@DeleteMapping("/deletePost/{forum}")
	public ResponseEntity<HttpStatus> deleteAllPostBasedonAdminForum(@PathVariable("forum") int forum)
	{
		postRepos.deletePostByForum(forum);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	 
}
