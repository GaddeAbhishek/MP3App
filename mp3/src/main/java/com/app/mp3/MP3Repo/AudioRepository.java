package com.app.mp3.MP3Repo;


import com.app.mp3.MP3Model.Audio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepository extends MongoRepository<Audio, String> {
}