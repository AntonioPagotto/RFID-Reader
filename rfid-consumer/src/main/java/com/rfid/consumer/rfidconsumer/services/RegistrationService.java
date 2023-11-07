package com.rfid.consumer.rfidconsumer.services;


import com.rfid.consumer.rfidconsumer.entities.Configuration;
import com.rfid.consumer.rfidconsumer.entities.TagRegistration;
import com.rfid.consumer.rfidconsumer.repository.ConfigurationRepository;
import com.rfid.consumer.rfidconsumer.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public TagRegistration getLastTagInRegistration() {
        return registrationRepository.findAll().get(0);
    }

    public void setLastTagInRegistration(TagRegistration tagRegistration) {
        registrationRepository.deleteAll();
        registrationRepository.save(tagRegistration);
    }

}
