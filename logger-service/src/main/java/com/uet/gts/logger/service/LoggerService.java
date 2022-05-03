package com.uet.gts.logger.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uet.gts.common.dto.KafkaEventDTO;
import com.uet.gts.logger.config.KafkaConsumerConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggerService {

    @SneakyThrows
    @KafkaListener(topics = KafkaConsumerConfig.TOPIC_NAME, containerFactory = "objectKafkaListenerContainerFactory")
    public void receivedMessage(KafkaEventDTO message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(message);
            log.info("Json message received using Kafka listener " + jsonString);
        } catch(Exception e) {
            log.error("Error happened: " + e.getMessage());
        }
    }
}
