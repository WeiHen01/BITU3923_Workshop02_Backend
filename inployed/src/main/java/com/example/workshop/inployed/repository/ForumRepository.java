package com.example.workshop.inployed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.workshop.inployed.model.Forum;

public interface ForumRepository extends JpaRepository<Forum, Integer> {

	@Query(value="SELECT * FROM forum "
			+ " WHERE ForumID = :forumId",
			nativeQuery = true)
	public Forum findByForumId(@Param("forumId")int forumID);
	
	@Query(value="SELECT * FROM forum"
			+" WHERE AdminID = :adminId",
			nativeQuery = true)
	public List<Forum> findAllundercertainAdmin(@Param("adminId") int adminID);
	

}
