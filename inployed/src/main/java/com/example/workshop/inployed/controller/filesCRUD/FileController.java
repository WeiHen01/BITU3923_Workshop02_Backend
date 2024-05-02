package com.example.workshop.inployed.controller.filesCRUD;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.workshop.inployed.model.filesCRUD.FileEntity;
import com.example.workshop.inployed.model.filesCRUD.FileResponse;
import com.example.workshop.inployed.service.filesCRUD.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadSingleFile/{userId}")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
    		@PathVariable("userId") int user) {
        try { 
        	/**
        	 * LOGGER: used to display string in Console tab
        	 */
            LOGGER.info("---------------------------------");
        	LOGGER.info("    Uploading file to server...  ");
        	LOGGER.info("---------------------------------");
            fileService.save(file, user);

            LOGGER.info("File uploaded.");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            LOGGER.error("Could not upload the file: {}!", file.getOriginalFilename());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

   /**
    * This method update user resume file based on user id
    * @param file
    * @param id
    * @return
    */
    @PutMapping("/updateFile/{id}")
    public ResponseEntity<String> update(@RequestParam("file") MultipartFile file, @PathVariable int id) {
    	System.out.println(id);
        try {
            LOGGER.info("Updating the file {}", file.getOriginalFilename());

            Optional<FileEntity> fileEntityOptional = fileService.getUserResume(id);

            if (!fileEntityOptional.isPresent()) {
                LOGGER.info("File is not found!");
                return ResponseEntity.notFound()
                        .build();
            }

            fileService.updateResume(file, id);

            LOGGER.info("File is updated: {}", file.getOriginalFilename());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File updated successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            LOGGER.error("Could not update the file: {}!", file.getOriginalFilename());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not update the file: %s!", file.getOriginalFilename()));
        }
    }
    
    @GetMapping("/getFile/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {

        LOGGER.info("Loading the file: {}", id);

        Optional<FileEntity> fileEntityOptional = fileService.getFile(id);

        if (!fileEntityOptional.isPresent()) {
            LOGGER.info("File is not found: {}", id);
            return ResponseEntity.notFound()
                    .build();
        }

        FileEntity fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
    }
    
    /**
     * View user resume
     * @param id
     * @return
     */
    @GetMapping("/getResume/{id}")
    public ResponseEntity<byte[]> getUserResume(@PathVariable int id) {

        LOGGER.info("Loading the file: {}", id);

        Optional<FileEntity> fileEntityOptional = fileService.getUserResume(id);

        if (!fileEntityOptional.isPresent()) {
            LOGGER.info("File is not found: {}", id);
            return ResponseEntity.notFound()
                    .build();
        }

        FileEntity fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
    }
    
    @GetMapping("/getResumeDetails/{id}")
    public ResponseEntity<?> getFileDetailsById(@PathVariable int id) {
        try {
            // Call the service to retrieve file details by ID
            Optional<FileEntity> fileEntity = fileService.getUserResume(id);
            
            if (fileEntity.isPresent()) {
                return new ResponseEntity<>(fileEntity.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @DeleteMapping("/deleteFile/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        try {
            LOGGER.info("Deleting the file: {}", id);

            Optional<FileEntity> fileEntityOptional = fileService.getFile(id);

            if (!fileEntityOptional.isPresent()) {
                LOGGER.info("File is not found: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("File is not Found!");
            }

            fileService.deleteFile(id);

            LOGGER.info("File is deleted: {}", id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private FileResponse mapToFileResponse(FileEntity fileEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/getFile/")
                .path(fileEntity.getId().toString())
                .toUriString();
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(fileEntity.getId().toString());
        fileResponse.setName(fileEntity.getName());
        fileResponse.setContentType(fileEntity.getContentType());
        fileResponse.setSize(fileEntity.getSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }
    
    /**
     * Download a file based on its ID
     * @param id The ID of the file to download
     * @return The file as a downloadable resource
     */
    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id) {
        try {
            LOGGER.info("Downloading the file: {}", id);

            Optional<FileEntity> fileEntityOptional = fileService.getUserResume(id);

            if (!fileEntityOptional.isPresent()) {
                LOGGER.info("File is not found: {}", id);
                return ResponseEntity.notFound().build();
            }

            FileEntity fileEntity = fileEntityOptional.get();
            ByteArrayResource resource = new ByteArrayResource(fileEntity.getData());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                    .contentType(MediaType.valueOf(fileEntity.getContentType()))
                    .body(resource);
        } catch (Exception e) {
            LOGGER.error("Error occurred while downloading the file: {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    

}
