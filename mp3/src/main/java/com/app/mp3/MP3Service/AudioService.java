package com.app.mp3.MP3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AudioService {

    @Autowired
    private GridFsTemplate gridFsTemplate;
    private GridFsOperations gridFsOperations;
    public List<String> listAudioFiles() {
        return StreamSupport.stream(gridFsTemplate.find(null).spliterator(), false)
                .map(gridFSFile -> gridFSFile.getFilename())
                .collect(Collectors.toList());
    }
    public String saveAudio(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        gridFsOperations.store(inputStream, filename, file.getContentType());
        return filename;
    }

    public InputStream getAudioStream(String filename) throws IOException {
        return gridFsOperations.getResource(filename).getInputStream();
    }
    public AudioService(GridFsOperations gridFsOperations) {
        this.gridFsOperations = gridFsOperations;
    }

}