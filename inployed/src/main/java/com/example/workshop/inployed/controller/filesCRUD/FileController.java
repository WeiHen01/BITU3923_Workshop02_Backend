package com.example.workshop.inployed.controller.filesCRUD;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.workshop.inployed.model.filesCRUD.FileEntity;
import com.example.workshop.inployed.service.filesCRUD.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadSingleFile")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try { 
        	/**
        	 * LOGGER: used to display string in Console tab
        	 */
            LOGGER.info("---------------------------------");
        	LOGGER.info("    Uploading file to server...  ");
        	LOGGER.info("---------------------------------");
            fileService.save(file);

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

   
    @PutMapping("/updateFile/{id}")
    public ResponseEntity<String> update(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        try {
            LOGGER.info("Updating the file {}", file.getOriginalFilename());

            Optional<FileEntity> fileEntityOptional = fileService.getFile(id);

            if (!fileEntityOptional.isPresent()) {
                LOGGER.info("File is not found!");
                return ResponseEntity.notFound()
                        .build();
            }

            fileService.update(file, id);

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

    @GetMapping("getFile/{id}")
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

    @DeleteMapping("deleteFile/{id}")
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

    

}
