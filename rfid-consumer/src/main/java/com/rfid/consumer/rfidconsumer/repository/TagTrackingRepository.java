package com.rfid.consumer.rfidconsumer.repository;

import com.rfid.consumer.rfidconsumer.entities.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagTrackingRepository extends MongoRepository<Tag, String> {


    Tag findByTagId(String tagId);


}