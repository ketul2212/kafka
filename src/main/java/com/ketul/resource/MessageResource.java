package com.ketul.resource;

import com.ketul.module.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageResource {

    @Autowired
    private KafkaTemplate<String, String> template;

    @PostMapping("/sendMessage")
    public ResponseEntity<Object> sendMessage(@RequestBody Request request) {
        return ResponseEntity.ok(template.send(request.getTopic(),"100", request.getMessage()));
    }
}
