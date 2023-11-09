package com.rfid.consumer.rfidconsumer.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tag")
public class Tag {
    @Id
    private String id;
    private String tagId;
    private String name;
    private String lastAntenna;
    private String place;
    private String responsibleName;
    private String responsibleEmail;
    private String state;
    private String date;

    public Tag(String tagId, String name, String lastAntenna, String responsibleName, String responsibleEmail, String state, String date) {
        this.tagId = tagId;
        this.name = name;
        this.lastAntenna = lastAntenna;
        this.date = date;
        this.place = (state == null) ? null : (state.equals("in") ? ("Dentro da sala " + lastAntenna) : ("Corredor"));
        this.responsibleName = responsibleName;
        this.responsibleEmail = responsibleEmail;
        this.state = state;
    }

    public Tag() {
    }

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

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getResponsibleEmail() {
        return responsibleEmail;
    }

    public void setResponsibleEmail(String responsibleEmail) {
        this.responsibleEmail = responsibleEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}