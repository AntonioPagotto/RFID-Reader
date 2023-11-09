package com.rfid.consumer.rfidconsumer.services;


import com.rfid.consumer.rfidconsumer.entities.Tag;
import com.rfid.consumer.rfidconsumer.entities.TagHistory;
import com.rfid.consumer.rfidconsumer.repository.TagHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HistoryService {

    private final TagHistoryRepository tagHistoryRepository;

    public HistoryService(TagHistoryRepository tagHistoryRepository) {
        this.tagHistoryRepository = tagHistoryRepository;
    }

    // Salva a movimentação da tag no banco de dados de historico
    public void saveTagHistory(Tag tag) {
        // Manipulando dados
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'as' HH:mm");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        TagHistory tagHistory = new TagHistory(tag.getTagId(), tag.getName(), tag.getLastAntenna(), tag.getResponsibleName(), tag.getResponsibleEmail(), tag.getState(), formattedDateTime);
        // Salvando no banco
        tagHistoryRepository.save(tagHistory);
    }

    // Busca o histórico de todas as tags
    public List<TagHistory> getTagsHistory() {
        return tagHistoryRepository.findAll();
    }

    // Busca o histórico de uma tag por tagId
    public List<TagHistory> getTagHistoryByTagId(String tagId) {
        return tagHistoryRepository.findByTagId(tagId);
    }

}
