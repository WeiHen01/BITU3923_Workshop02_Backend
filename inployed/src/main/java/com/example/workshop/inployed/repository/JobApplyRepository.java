package com.example.workshop.inployed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.workshop.inployed.model.JobApply;

public interface JobApplyRepository extends JpaRepository<JobApply, Integer> {

	@Query(value="SELECT * FROM job_apply "
			+ " WHERE UserID = :userId "
			+ " AND ApplyStatus != 'Hidden'",
			nativeQuery = true)
	public List<JobApply> ViewJobAppliedByUser(@Param("userId")int userID);
	
	@Query(value="SELECT * FROM job_apply "
			+ " WHERE AdsID = :adsId ",
			nativeQuery = true)
	public List<JobApply> ViewJobApplyStatus(@Param("adsId")int adsId);
	
	/**
	 * At company level, view the job requests based on own company job posts
	 * @param companyId
	 * @return
	 */
	@Query(value="SELECT * FROM job_apply WHERE AdsID IN "
			+ "(SELECT AdsID FROM job WHERE CompanyID = :companyId) "
			+ "AND ApplyStatus = 'Not approved yet'",
			nativeQuery = true)
	public List<JobApply> ViewJobRequestsList(@Param("companyId")int companyId);
	
}
