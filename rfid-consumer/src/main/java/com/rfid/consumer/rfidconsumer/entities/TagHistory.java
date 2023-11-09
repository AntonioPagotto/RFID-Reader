package com.rfid.consumer.rfidconsumer.entities;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TagHistory")
public class TagHistory extends Tag {

    public TagHistory(String tagId, String name, String lastAntenna, String responsibleName, String responsibleEmail, String state, String date) {
        super(tagId, name, lastAntenna, responsibleName, responsibleEmail, state, date);
    }
}