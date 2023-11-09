package com.rfid.consumer.rfidconsumer.services;


import com.rfid.consumer.rfidconsumer.entities.Tag;
import com.rfid.consumer.rfidconsumer.repository.TagStorageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TagStorageService {

    private final TagStorageRepository tagStorageRepository;

    private final HistoryService historyService;

    public TagStorageService(TagStorageRepository tagStorageRepository, HistoryService historyService) {
        this.tagStorageRepository = tagStorageRepository;
        this.historyService = historyService;
    }

    // Verifica se uma tag é registrada por tagId
    public boolean verifyIfIsAnRegistratedTag(String tagId) {

        return tagStorageRepository.existsByTagId(tagId);
    }

    // Retorna todas as tags salvas
    public List<Tag> getAllTags() {
        return tagStorageRepository.findAll();
    }


    // Método para rastrear a tag
    public Tag trackTag(String tagId, String antenna) {

        // Busca a tag no banco de dados de storage
        Tag tag = tagStorageRepository.findByTagId(tagId);

        //Se a antena captada é igual a ultima antena lida, entao apenas alteramos o estado de in para out e vice-versa
        if(tag.getLastAntenna().equals(antenna)){
            tag.setState(tag.getState().equals("in") ? "out" : "in");

        //Se a antena captada é diferente da ultima antena lida, entao alteramos o estado para in e setamos a nova antena
        } else {
            tag.setState("in");
            tag.setLastAntenna(antenna);
        }

        tag.setPlace((tag.getState() == null) ? null : (tag.getState().equals("in") ? ("Dentro da sala " + tag.getLastAntenna()) : ("Corredor")));

        // Salva a movimentação no histórico
        historyService.saveTagHistory(tag);

        // Salvando o estado atual da tag
        return tagStorageRepository.save(tag);
    }


}
