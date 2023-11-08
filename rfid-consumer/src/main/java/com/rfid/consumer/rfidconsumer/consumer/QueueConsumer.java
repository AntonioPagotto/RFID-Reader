package com.rfid.consumer.rfidconsumer.consumer;

import com.rfid.consumer.rfidconsumer.entities.Tag;
import com.rfid.consumer.rfidconsumer.services.ConfigurationService;
import com.rfid.consumer.rfidconsumer.services.EmailService;
import com.rfid.consumer.rfidconsumer.services.RegistrationService;
import com.rfid.consumer.rfidconsumer.services.TagStorageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class QueueConsumer {

    private final ConfigurationService configurationService;
    private final RegistrationService registrationService;
    private final TagStorageService tagStorageService;
    private final EmailService emailService;

    public QueueConsumer
            (ConfigurationService configurationService,
             RegistrationService registrationService,
             TagStorageService tagStorageService,
             EmailService emailService) {
        this.configurationService = configurationService;
        this.registrationService = registrationService;
        this.tagStorageService = tagStorageService;
        this.emailService = emailService;
    }

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String message) throws RuntimeException {
        String[] parts = message.split(" ");
        String tagId = parts[0];
        String antenna = parts[1];

        System.out.println(message + "  " + LocalDateTime.now());

        String configStateType = configurationService.getConfiguration();


        if(configStateType.equals("registry")){
            Tag tagRegistration = new Tag();
            tagRegistration.setLastAntenna(antenna);
            tagRegistration.setTagId(tagId);
            registrationService.setTagIdAndAntenna(tagRegistration);
        }


        //Se o modo de configuração estiver como "tracking" e a tag lida for registrada, entao...
        if(configStateType.equals("tracking") && tagStorageService.verifyIfIsAnRegistratedTag(tagId)){

            tagStorageService.trackTag(tagId, antenna);

            // Enviar e-mail....
//            emailService.sendSimpleMessage("antoniopagotto121@gmail.com", "RFID - Tag Tracking", "A tag " + tagId + " foi lida na antena " + antenna + "!");

        }

    }

}