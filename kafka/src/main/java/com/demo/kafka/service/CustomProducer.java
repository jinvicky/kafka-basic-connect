package com.demo.kafka.service;


import com.demo.kafka.config.KafkaConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomProducer {

    private final KafkaConfig config;
    private KafkaProducer<String, String> producer = null;

    @PostConstruct
    public void build() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, config.getProducer().getKeySerializer());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, config.getProducer().getValueSerializer());
        producer = new KafkaProducer<>(properties);
    }

    public void send(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(config.getTemplate().getDefaultTopic(), message);
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                log.info("메세지 전송 완료: {}", message);
                if(exception!=null) {
                    log.info(exception.getMessage());
                }
            }
        });
    }
}
