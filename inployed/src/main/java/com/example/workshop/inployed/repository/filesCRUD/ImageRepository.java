package com.example.workshop.inployed.repository.filesCRUD;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.workshop.inployed.model.filesCRUD.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

	/**
	 * Handling get image where type = "application/octet-stream"
	 * @param userId
	 * @return
	 */
	@Query(value="SELECT * FROM user_image "
			+ " WHERE UserID = :UserID"
			+ " AND img_type LIKE 'application/octet-stream%'", 
			nativeQuery = true)
	public Optional<ImageEntity> findUserOctetProfileImage(@Param("UserID")int userId);
	
	/**
	 * Handling get image where type = "image/(jpeg,png...)"
	 * @param userId
	 * @return
	 */
	@Query(value="SELECT * FROM user_image "
			+ " WHERE UserID = :UserID"
			+ " AND img_type LIKE 'image%'", 
			nativeQuery = true)
	public Optional<ImageEntity> findUserProfileImage(@Param("UserID")int userId);

	
	@Query(value="SELECT * FROM user_image "
			+ " WHERE UserID = :UserID"
			+ " AND img_type LIKE 'application/octet-stream%'", 
			nativeQuery = true)
	public ImageEntity findUserOctetStreamProfileImage(@Param("UserID")int userId);
	
	
	@Query(value="SELECT * FROM user_image "
			+ " WHERE UserID = :UserID"
			+ " AND img_type LIKE 'image%'", 
			nativeQuery = true)
	public ImageEntity findUserViewProfileImage(@Param("UserID")int userId);
	
}
