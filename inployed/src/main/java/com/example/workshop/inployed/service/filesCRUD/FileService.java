package com.example.workshop.inployed.service.filesCRUD;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.workshop.inployed.model.filesCRUD.FileEntity;
import com.example.workshop.inployed.repository.filesCRUD.FileRepository;


@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public void save(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(java.util.UUID.randomUUID().toString());
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setData(file.getBytes());
        

        fileRepository.save(fileEntity);
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

    public void update(MultipartFile file, Long id) throws IOException {
        FileEntity fileEntity = fileRepository.getById(id);
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setData(file.getBytes());

        fileRepository.save(fileEntity);
    }
    
    

}