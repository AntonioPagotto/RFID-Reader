package com.rfid.consumer.rfidconsumer.controller;


import com.rfid.consumer.rfidconsumer.entities.Tag;
import com.rfid.consumer.rfidconsumer.services.RegistrationService;
import com.rfid.consumer.rfidconsumer.services.TagStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/tag-tracking")
public class TagStorageController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private TagStorageService tagStorageService;

    @GetMapping
    public List<Tag> getAllTags(){
        return tagStorageService.getAllTags();
    }

}
