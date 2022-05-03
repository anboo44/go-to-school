package com.uet.gts.logger.config;

import com.uet.gts.common.dto.KafkaEventDTO;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    public static final String GROUP_ID_STRING = "group-id-string";
    public static final String GROUP_ID_JSON = "group-id-json";
    public static final String TOPIC_NAME = "report-logger";

    @Bean
    public ConsumerFactory<String, String> consumingEventStringMessage() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_STRING);
        return new DefaultKafkaConsumerFactory<>(configMap);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> listenerEventSendStringMessage() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumingEventStringMessage());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, KafkaEventDTO> consumingEventSendObjectMessage() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_JSON);
        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.uet.gts.common.dto");
        return new DefaultKafkaConsumerFactory<>(configMap);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaEventDTO> objectKafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, KafkaEventDTO>();
        factory.setConsumerFactory(consumingEventSendObjectMessage());
        return factory;
    }
}
