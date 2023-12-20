package com.example.workshop.inployed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.workshop.inployed.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query(value="SELECT * FROM company "
			+ " WHERE companyId = :companyId",
			nativeQuery = true)
	public Company findByCompanyId(@Param("companyId")int company);
}
