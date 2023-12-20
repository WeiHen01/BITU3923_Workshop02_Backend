package com.example.workshop.inployed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.workshop.inployed.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

	@Query(value="SELECT * FROM job "
			+ " WHERE AdsId = :adsId",
			nativeQuery = true)
	public Job findByJobId(@Param("adsId")int userID);
	
	@Query(value="SELECT * FROM job "
			+ " WHERE JobPosition LIKE %:name%",
			nativeQuery = true)
	public List<Job> findJobByName(@Param("name")String name);
	
	
	@Query(value="SELECT * FROM job "
			+ " WHERE CompanyID = :company",
			nativeQuery = true)
	public List<Job> findCompanyOwnJob(@Param("company")int company);
}
