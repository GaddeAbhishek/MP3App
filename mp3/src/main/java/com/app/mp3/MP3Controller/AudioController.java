package com.app.mp3.MP3Controller;

import com.app.mp3.MP3Service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/audio")
public class AudioController {

    @Autowired
    private AudioService audioService;
    @Autowired
    private GridFsTemplate gridFsTemplate; // Autowire GridFsTemplate here

    @GetMapping("/list")
    public ModelAndView listAudioFiles() {
        List<String> audioFiles = new ArrayList<>();
        Query query = new Query(); // Create a new query
        gridFsTemplate.find(query).forEach(gridFSFile -> {
            audioFiles.add(gridFSFile.getFilename());
        });
        ModelAndView modelAndView = new ModelAndView("audio_list");
        modelAndView.addObject("audioFiles", audioFiles);
        return modelAndView;
    }
    @PostMapping("/upload")
    public String uploadAudio(@RequestParam("File") MultipartFile file) throws IOException {
        return audioService.saveAudio(file);
    }

    @GetMapping(value = "/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getAudio(@PathVariable String filename) throws IOException {
        InputStream audioStream = audioService.getAudioStream(filename);
        return audioStream.readAllBytes();
    }

}
