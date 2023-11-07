package com.rfid.consumer.rfidconsumer.services;


import com.rfid.consumer.rfidconsumer.entities.Tag;
import com.rfid.consumer.rfidconsumer.repository.TagStorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagStorageService {

    private final TagStorageRepository tagStorageRepository;

    public TagStorageService(TagStorageRepository tagStorageRepository) {
        this.tagStorageRepository = tagStorageRepository;
    }

    public boolean verifyIfIsAnRegistratedTag(String tagId) {

        return tagStorageRepository.existsByTagId(tagId);
    }

    public void registerTag() {
        Tag tag = new Tag("HE2001093", "Adesivo", null, "Antonio", "antoniopagotto121@gmail.com", "in");
        tagStorageRepository.save(tag);
    }

    public List<Tag> getAllTags() {
        return tagStorageRepository.findAll();
    }


}
