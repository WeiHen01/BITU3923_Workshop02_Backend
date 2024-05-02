package com.example.workshop.inployed.controller.filesCRUD;

import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.workshop.inployed.model.filesCRUD.ImageEntity;
import com.example.workshop.inployed.service.filesCRUD.ImageService;

import jakarta.servlet.annotation.MultipartConfig;

@RestController
@MultipartConfig
@RequestMapping("/image")
public class ImageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private ImageService imageService;
	
    @PostMapping("/uploadSingleImage/{userId}")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file,
    		@PathVariable("userId") int user) {
        try { 
        	/**
        	 * LOGGER: used to display string in Console tab
        	 */
            LOGGER.info("---------------------------------");
        	LOGGER.info("    Uploading file to server...  ");
        	LOGGER.info("---------------------------------");
        	imageService.save(file, user);

            LOGGER.info("Image uploaded.");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("Image uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            LOGGER.error("Could not upload the file: {}!", file.getOriginalFilename());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the image: %s!", file.getOriginalFilename()));
        }
    }
	
	/**
     * This method update user profile image based on user id
     * @param file
     * @param id
     * @return
     */
     @PutMapping("/updateImage/{id}")
     public ResponseEntity<String> updateImage(@RequestParam("image") MultipartFile file, @PathVariable("id") int id) {
     	System.out.println("Updating user: " + id + " profile image");
        try {
            LOGGER.info("Updating the image {}", file.getOriginalFilename());

            Optional<ImageEntity> fileEntityOptional = imageService.getUserOctetProfileImage(id);
            Optional<ImageEntity> fileOptional = imageService.getUserProfileImage(id);
            if (!fileEntityOptional.isPresent() && !fileOptional.isPresent()) {
                LOGGER.info("Image is not found for user: {}", id);
                return ResponseEntity.notFound().build();
            }
            
            imageService.updateImage(file, id);
            LOGGER.info("Image is updated: {}", file.getOriginalFilename());
            return ResponseEntity.ok(String.format("Image updated successfully: %s", file.getOriginalFilename()));
            
        } catch (Exception e) { 
            LOGGER.error("Could not update the image: {}!", file.getOriginalFilename(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not update the image: %s due to %s", file.getOriginalFilename(), e.getMessage()));
        }
     }
     
     /**
      * View user profile
      * @param id
      * @return
      */
     @GetMapping("/getProfileImage/{id}")
     public ResponseEntity<byte[]> getUserImage(@PathVariable int id) {

         LOGGER.info("Loading the image: {}", id);

        
         Optional<ImageEntity> imageEntityOptional = imageService.getUserProfileImage(id);
         Optional<ImageEntity> imageOptional = imageService.getUserOctetProfileImage(id);
         
         if (imageEntityOptional.isPresent()) {
        	 ImageEntity fileEntity = imageEntityOptional.get();
             return ResponseEntity.ok()
                     .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                     .contentType(MediaType.valueOf(fileEntity.getContentType()))
                     .body(fileEntity.getData());
         }
         else if (imageOptional.isPresent()) {
        	 ImageEntity fileEntity = imageOptional.get();
             return ResponseEntity.ok()
                     .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                     .contentType(MediaType.valueOf(fileEntity.getContentType()))
                     .body(fileEntity.getData());
         }
         else {
        	 LOGGER.info("Image is not found: {}", id);
             return ResponseEntity.notFound()
                     .build();
         }

     }
     
     
}
