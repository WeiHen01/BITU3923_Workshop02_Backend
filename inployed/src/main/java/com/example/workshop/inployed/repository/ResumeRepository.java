package com.example.workshop.inployed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.workshop.inployed.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

	@Query(value="SELECT * FROM resume "
			+ " WHERE UserID = :userId",
			nativeQuery = true)
	public Resume retrieveAUserResume(@Param("userId")int userID);
}
