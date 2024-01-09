package com.example.workshop.inployed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	/**
	 * Newly added
	 * 10 January 2023
	 * Delete Forum post based on Forum ID
	 * @param forum
	 */
	@Modifying
	@Query(value="DELETE FROM forum_post "
			+ " WHERE ForumID = :forumId", nativeQuery = true)
	public void deletePostByForum (@Param("forumId")int forum);
	
}
