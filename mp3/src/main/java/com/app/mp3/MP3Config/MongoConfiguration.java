package com.app.mp3.MP3Config;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;

@Configuration

public class MongoConfiguration {

    @Autowired
    private MongoDatabaseFactory mongoDatabaseFactory;

    @Bean
    public GridFSBucket gridFSBucket() {
        MongoDatabase database = mongoDatabaseFactory.getMongoDatabase();
        return GridFSBuckets.create(database);
    }
}
