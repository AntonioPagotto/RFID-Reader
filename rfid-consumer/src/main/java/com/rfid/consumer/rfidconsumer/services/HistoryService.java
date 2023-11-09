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

    public void saveTagHistory(Tag tag) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'as' HH:mm");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        TagHistory tagHistory = new TagHistory(tag.getTagId(), tag.getName(), tag.getLastAntenna(), tag.getResponsibleName(), tag.getResponsibleEmail(), tag.getState(), formattedDateTime);
        tagHistoryRepository.save(tagHistory);
    }

    public List<TagHistory> getTagsHistory() {
        return tagHistoryRepository.findAll();
    }

    public List<TagHistory> getTagHistoryByTagId(String tagId) {
        return tagHistoryRepository.findByTagId(tagId);
    }

}
