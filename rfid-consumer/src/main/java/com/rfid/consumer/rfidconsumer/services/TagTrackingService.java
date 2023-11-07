package com.rfid.consumer.rfidconsumer.services;


import com.rfid.consumer.rfidconsumer.entities.Tag;
import com.rfid.consumer.rfidconsumer.repository.TagStorageRepository;
import com.rfid.consumer.rfidconsumer.repository.TagTrackingRepository;
import org.springframework.stereotype.Service;

@Service
public class TagTrackingService {

    private final TagTrackingRepository tagTrackingRepository;


    TagTrackingService(TagTrackingRepository tagTrackingRepository) {
        this.tagTrackingRepository = tagTrackingRepository;
    }


    public void trackTag(String tagId, String antenna) {

        Tag tag = tagTrackingRepository.findByTagId(tagId);

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
        tagTrackingRepository.save(tag);

    }

}
