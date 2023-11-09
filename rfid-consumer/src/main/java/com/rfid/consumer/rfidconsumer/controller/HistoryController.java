package com.rfid.consumer.rfidconsumer.controller;


import com.rfid.consumer.rfidconsumer.entities.Tag;
import com.rfid.consumer.rfidconsumer.entities.TagHistory;
import com.rfid.consumer.rfidconsumer.services.HistoryService;
import com.rfid.consumer.rfidconsumer.services.RegistrationService;
import com.rfid.consumer.rfidconsumer.services.TagStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping
    public List<TagHistory> getTagsHistory(){
        return historyService.getTagsHistory();
    }

    @GetMapping(path = "/{id}")
    public List<TagHistory> getTagHistoryByTagId(@PathVariable String id){
        return historyService.getTagHistoryByTagId(id);
    }

}
