package com.rfid.consumer.rfidconsumer.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TagRegistration")
public class TagRegistration extends Tag{

}