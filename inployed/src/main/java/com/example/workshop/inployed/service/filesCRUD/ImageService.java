package com.example.workshop.inployed.service.filesCRUD;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.workshop.inployed.model.User;
import com.example.workshop.inployed.model.filesCRUD.ImageEntity;
import com.example.workshop.inployed.repository.filesCRUD.ImageRepository;

@Service
public class ImageService {

	 @Autowired
	 private ImageRepository imageRepository;

	 public void save(MultipartFile file, int userId) throws IOException {
		 ImageEntity image = new ImageEntity();
	     image.setId(java.util.UUID.randomUUID().toString());
	     image.setName(StringUtils.cleanPath(file.getOriginalFilename()));
	     image.setContentType(file.getContentType());
	     image.setSize(file.getSize());
	     image.setData(file.getBytes());
	        
	     // Set the user ID by creating a User object with the provided ID
	     User user = new User();
	     user.setUserId(userId);
	     image.setUserID(user);

	     imageRepository.save(image);
	 }
	 
	 public Optional<ImageEntity> getUserProfileImage(int userid){
		 return imageRepository.findUserProfileImage(userid);
	 }
	 
	 public Optional<ImageEntity> getUserOctetProfileImage(int userid){
		 return imageRepository.findUserOctetProfileImage(userid);
	 }
	    
	 public Optional<ImageEntity> getFile(int id) {
	     return imageRepository.findById(id);
	 }
	    

	    public List<ImageEntity> getAllFiles() {
	        return imageRepository.findAll();
	    }
	    
	    public List<ImageEntity> getAllFilesList() {
	        return imageRepository.findAll();
	    }

	    public void deleteFile(int id) {
	    	imageRepository.deleteById(id);
	    } 

	    public void updateImage(MultipartFile file, int id) throws IOException {
	    	ImageEntity image = null;
	    	
	    	if(imageRepository.findUserOctetStreamProfileImage(id) != null) {
	    		image = imageRepository.findUserOctetStreamProfileImage(id);
	    	}
	    	else {
	    		image = imageRepository.findUserViewProfileImage(id);
	    	}
	        
	        System.out.println(id);
	        
	        image.setId(java.util.UUID.randomUUID().toString());
	        image.setName(StringUtils.cleanPath(file.getOriginalFilename()));
	        image.setContentType(file.getContentType());
	        image.setSize(file.getSize());
	        image.setData(file.getBytes());
	        
	        imageRepository.save(image);
	    }
}
