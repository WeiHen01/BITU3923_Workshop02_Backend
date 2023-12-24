package com.example.workshop.inployed.repository.filesCRUD;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.workshop.inployed.model.filesCRUD.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

	@Query(value="SELECT * FROM user_files "
			+ " WHERE UserID = :UserID"
			+ " AND type = 'application/pdf'", 
			nativeQuery = true)
	public Optional<FileEntity> findUserResume(@Param("UserID")int userId);
	 
	
	@Query(value="SELECT * FROM user_files "
			+ " WHERE UserID = :UserID"
			+ " AND type = 'application/pdf'", 
			nativeQuery = true)
	public FileEntity findResumebyUser(@Param("UserID")int userId);
}
