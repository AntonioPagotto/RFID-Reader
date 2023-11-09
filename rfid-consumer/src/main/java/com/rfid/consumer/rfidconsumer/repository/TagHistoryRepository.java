package com.rfid.consumer.rfidconsumer.repository;

import com.rfid.consumer.rfidconsumer.entities.TagHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TagHistoryRepository extends MongoRepository<TagHistory, String> {

    List<TagHistory> findByTagId(String tagId);

}