package com.rfid.consumer.rfidconsumer.services;


import com.rfid.consumer.rfidconsumer.entities.Configuration;
import com.rfid.consumer.rfidconsumer.entities.Tag;
import com.rfid.consumer.rfidconsumer.entities.TagRegistration;
import com.rfid.consumer.rfidconsumer.repository.ConfigurationRepository;
import com.rfid.consumer.rfidconsumer.repository.RegistrationRepository;
import com.rfid.consumer.rfidconsumer.repository.TagStorageRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    private final ConfigurationRepository configurationRepository;

    private final TagStorageRepository tagStorageRepository;

    public RegistrationService(RegistrationRepository registrationRepository, ConfigurationRepository configurationRepository, TagStorageRepository tagStorageRepository) {
        this.registrationRepository = registrationRepository;
        this.configurationRepository = configurationRepository;
        this.tagStorageRepository = tagStorageRepository;
    }

    public TagRegistration getLastTagInRegistration() {
        return registrationRepository.findAll().get(0);
    }

    public void setTagIdAndAntenna(Tag tagRegistration) {

        TagRegistration tag = registrationRepository.findAll().get(0);
        tag.setLastAntenna(tagRegistration.getLastAntenna());
        tag.setTagId(tagRegistration.getTagId());

        registrationRepository.deleteAll();
        tagStorageRepository.deleteByTagId(tag.getTagId());
        tagStorageRepository.save(tag);

        Configuration configuration = new Configuration("tracking");
        configurationRepository.deleteAll();
        configurationRepository.save(configuration);

    }

    public void setTagNameAndResponsible(String tagName, String responsibleName, String responsibleEmail) {

//        TagRegistration tagRegistration = registrationRepository.findAll().get(0);
        TagRegistration tagRegistration = new TagRegistration();
        tagRegistration.setName(tagName);
        tagRegistration.setResponsibleName(responsibleName);
        tagRegistration.setResponsibleEmail(responsibleEmail);
        tagRegistration.setState("not calibrated");

        registrationRepository.deleteAll();
        registrationRepository.save(tagRegistration);

        //Settando modo de leitura para registro
        Configuration configuration = new Configuration("registry");
        configurationRepository.deleteAll();
        configurationRepository.save(configuration);
    }


}
