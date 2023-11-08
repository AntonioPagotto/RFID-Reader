package com.rfid.consumer.rfidconsumer.controller;


import com.rfid.consumer.rfidconsumer.entities.TagRegistrationRequest;
import com.rfid.consumer.rfidconsumer.services.ConfigurationService;
import com.rfid.consumer.rfidconsumer.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(path = "/register-tag")
    public void registerTag(@RequestBody TagRegistrationRequest request){
        registrationService.setTagNameAndResponsible(request.getTagName(), request.getResponsibleName(), request.getResponsibleEmail());
    }

}
