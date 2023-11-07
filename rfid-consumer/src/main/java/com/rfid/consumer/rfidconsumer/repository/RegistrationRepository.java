package com.rfid.consumer.rfidconsumer.repository;

import com.rfid.consumer.rfidconsumer.entities.TagRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegistrationRepository extends MongoRepository<TagRegistration, String> {
}