package com.rfid.consumer.rfidconsumer.services;


import com.rfid.consumer.rfidconsumer.entities.Configuration;
import com.rfid.consumer.rfidconsumer.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    // Busca no banco de dados o método de leitura atual (REGISTER ou TRACKING)
    public String getConfiguration() {

        Configuration config = configurationRepository.findAll().get(0);

        return config.getConfigStateType();
    }


}
