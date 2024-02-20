package com.app.mp3.MP3Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fs.files")
public class Audio {
    @Id
    private String id;

    // Add other properties if needed

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getters and setters
}
