package com.rfid.consumer.rfidconsumer.repository;

import com.rfid.consumer.rfidconsumer.entities.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagStorageRepository extends MongoRepository<Tag, String> {

    boolean existsByTagId(String tagId);

    Tag findByTagId(String tagId);

    void deleteByTagId(String tagId);

}