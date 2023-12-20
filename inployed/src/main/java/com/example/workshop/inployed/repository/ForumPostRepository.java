package com.example.workshop.inployed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.workshop.inployed.model.ForumPost;

public interface ForumPostRepository extends JpaRepository<ForumPost, Long> {

	/**
	 * View forum post based on forum 
	 * @param forumID
	 * @return
	 */
	@Query(value="SELECT * FROM forum_post "
			+ " WHERE ForumID = :forumId",
			nativeQuery = true)
	public List<ForumPost> findByForumId(@Param("forumId")int forumID);
	
	
	@Query(value="SELECT * FROM forum_post "
			+ " WHERE ForumID = :forumId "
			+ " AND UserID = :userId",
			nativeQuery = true)
	public List<ForumPost> findForumPostbyUserId(@Param("forumId")int forumID, 
			@Param("userId")int user);
	
}
