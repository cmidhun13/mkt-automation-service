package com.szells.marketing.automation.service.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sagar
 */
@Slf4j
@Configuration
public class MessageProducerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapAddress;

    private static final String ENABLE_AUTO_COMMIT = "enable.auto.commit";

    @Bean
    public Map<String,Object> producerConfig(){
        Map<String,Object> configProps=new HashMap<String,Object>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
       // configProps.put(ENABLE_AUTO_COMMIT, false);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return configProps;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(){

        return new KafkaTemplate<>(producerFactory());
    }
}


