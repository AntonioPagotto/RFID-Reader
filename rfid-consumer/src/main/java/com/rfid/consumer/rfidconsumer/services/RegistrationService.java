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


    // Seta tagId e antenna para a Tag que está em registro
    public void setTagIdAndAntenna(Tag tagRegistration) {

        TagRegistration tag = registrationRepository.findAll().get(0);
        tag.setLastAntenna(tagRegistration.getLastAntenna());
        tag.setTagId(tagRegistration.getTagId());

        registrationRepository.deleteAll();
        tagStorageRepository.deleteByTagId(tag.getTagId());
        tagStorageRepository.save(tag);

        // Setando modo de leitura para TRACKING
        Configuration configuration = new Configuration("TRACKING");
        configurationRepository.deleteAll();
        configurationRepository.save(configuration);

    }

    // Seta nome, responsável e email do responsável para tag em registro
    public void setTagNameAndResponsible(String tagName, String responsibleName, String responsibleEmail) {

        TagRegistration tagRegistration = new TagRegistration();
        tagRegistration.setName(tagName);
        tagRegistration.setResponsibleName(responsibleName);
        tagRegistration.setResponsibleEmail(responsibleEmail);
        tagRegistration.setState("not calibrated");

        registrationRepository.deleteAll();
        registrationRepository.save(tagRegistration);

        // Setando modo de leitura para REGISTER
        Configuration configuration = new Configuration("REGISTER");
        configurationRepository.deleteAll();
        configurationRepository.save(configuration);
    }


}
