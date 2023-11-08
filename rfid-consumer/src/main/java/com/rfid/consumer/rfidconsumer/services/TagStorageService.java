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

    public void trackTag(String tagId, String antenna) {

        Tag tag = tagStorageRepository.findByTagId(tagId);

        //Se a antena captada é igual a ultima antena lida, entao apenas alteramos o estado de in para out e vice-versa
        if(tag.getLastAntenna().equals(antenna)){
            tag.setState(tag.getState().equals("in") ? "out" : "in");
            //Se a antena captada é diferente da ultima antena lida, entao alteramos o estado para in e setamos a nova antena
        } else {
            tag.setState("in");
            tag.setLastAntenna(antenna);
        }

        tag.setPlace((tag.getState() == null) ? null : (tag.getState().equals("in") ? ("Dentro de " + tag.getLastAntenna()) : ("Saiu de " + tag.getLastAntenna())));

        // salvamos a alteração no banco
        tagStorageRepository.save(tag);

    }


}
