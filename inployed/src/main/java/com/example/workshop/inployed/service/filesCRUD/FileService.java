package com.example.workshop.inployed.service.filesCRUD;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.workshop.inployed.model.User;
import com.example.workshop.inployed.model.filesCRUD.FileEntity;
import com.example.workshop.inployed.repository.filesCRUD.FileRepository;


@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public void save(MultipartFile file, int userId) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(java.util.UUID.randomUUID().toString());
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setData(file.getBytes());
        
        // Set the user ID by creating a User object with the provided ID
        User user = new User();
        user.setUserId(userId);
        fileEntity.setUserID(user);

        fileRepository.save(fileEntity);
    }

    
    public Optional<FileEntity> getUserResume(int userid){
    	return fileRepository.findUserResume(userid);
    }
    
    public Optional<FileEntity> getFile(Long id) {
        return fileRepository.findById(id);
    }
    

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }
    
    public List<FileEntity> getAllFilesList() {
        return fileRepository.findAll();
    }

    public void deleteFile(Long id) {
         fileRepository.deleteById(id);
    }

    public void updateResume(MultipartFile file, int id) throws IOException {
        FileEntity fileEntity = fileRepository.findResumebyUser(id);
        
        System.out.println(id);
        
        fileEntity.setId(java.util.UUID.randomUUID().toString());
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setData(file.getBytes());
        
        fileRepository.save(fileEntity);
    }
    

}