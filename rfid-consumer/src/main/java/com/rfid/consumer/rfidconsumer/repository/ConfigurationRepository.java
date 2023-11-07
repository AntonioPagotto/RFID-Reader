package com.rfid.consumer.rfidconsumer.repository;

import com.rfid.consumer.rfidconsumer.entities.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigurationRepository extends MongoRepository<Configuration, String> {
}