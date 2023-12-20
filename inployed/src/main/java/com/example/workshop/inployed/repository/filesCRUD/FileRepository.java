package com.example.workshop.inployed.repository.filesCRUD;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.workshop.inployed.model.filesCRUD.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

	
}
