package com.demo.kafka.controller;


import com.demo.kafka.service.CustomProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {

    @Autowired
    CustomProducer producer;

    @PostMapping("/send-alert")
    public void producer(@RequestBody Map<String, String> message) {
        String msg = message.get("msg");
        producer.send(msg);
    }
}
