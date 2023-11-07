package com.rfid.consumer.rfidconsumer.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TagTracking")
public class TagTracking {
    @Id
    private String id;
    private String tagId;
    private String name;
    private String lastAntenna;
    private String state; // in/out

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastAntenna() {
        return lastAntenna;
    }

    public void setLastAntenna(String lastAntenna) {
        this.lastAntenna = lastAntenna;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}