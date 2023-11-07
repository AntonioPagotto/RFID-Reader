package com.rfid.consumer.rfidconsumer.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Configuration")
public class Configuration {
    @Id
    private String id;

    private String configStateType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfigStateType() {
        return configStateType;
    }

    public void setConfigStateType(String configStateType) {
        this.configStateType = configStateType;
    }
}