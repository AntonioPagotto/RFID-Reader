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
import java.time.format.DateTimeFormatter;


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

    // Consumer responsável por ouvir a fila do RabbitMQ
    @RabbitListener(queues = "${queue.name}")
    public void receive(@Payload String message) {

        // Tratando a mensagem, e pegando tagId e antenna
        String[] parts = message.split(" ");
        String tagId = parts[0];
        String antenna = parts[1];

        // Lendo no banco de dados o tipo de leitura configurado (REGISTER ou TRACKING)
        String configStateType = configurationService.getConfiguration();

        switch (configStateType) {

            // Modo de leitura: REGISTER
            case "REGISTER":
                System.out.println("Registrando tag");
                registerTag(tagId, antenna);
                break;

            // Modo de leitura: TRACKING
            case "TRACKING":
                System.out.println("Rastreando tag");
                trackTag(tagId, antenna);
                break;

            default:
                System.out.println("Nenhuma configuração encontrada");
                break;
        }

    }

    // Registra tagId e antenna na Tag que está em modo registro
    private void registerTag(String tagId, String antenna) {
        Tag tagRegistration = new Tag();
        tagRegistration.setLastAntenna(antenna);
        tagRegistration.setTagId(tagId);
        registrationService.setTagIdAndAntenna(tagRegistration);
    }

    // Envia tagId e antenna captados para o método trackTag e envia um e-mail informando
    // ao responsável que houve um movimento do objeto
    private void trackTag(String tagId, String antenna) {
        if (tagStorageService.verifyIfIsAnRegistratedTag(tagId)) {
            Tag savedTag = tagStorageService.trackTag(tagId, antenna);
            // Envia e-mail
            sendEmail(savedTag);
        }
    }


    // Envia e-mail para o responsável
    private void sendEmail(Tag savedTag) {
        try {
            System.out.println("Enviando email");
            String subject = "Equipamento Rastreado";
            emailService.sendEmail(savedTag.getResponsibleEmail(), subject, savedTag);
        } catch (Exception e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }

}