package com.szells.marketing.automation.service.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.szells.marketing.automation.service.model.MarketingAutomationRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Configuration
@EnableKafka
public class MessageConsumerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapAddress;
    @Value("${kafka.consumer_groupId}")
    private String groupId;


    @Bean
    public Map<String,Object> consumerConfig(){
        Map<String,Object> consumerProperties=new HashMap<String,Object>();
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        consumerProperties.put("enable.partition.eof","false");
        return consumerProperties;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory=new
                ConcurrentKafkaListenerContainerFactory<String,String>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(Integer.parseInt("5"));
        return factory;
    }

    @Bean
    public ConsumerFactory<String, MarketingAutomationRequest> marketingAutomationRequestConsumerFactory(){
        Map<String,Object> consumerProperties=new HashMap<String,Object>();
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        return new DefaultKafkaConsumerFactory<>(consumerProperties, new StringDeserializer(), new org.springframework.kafka.support.serializer.JsonDeserializer<>(MarketingAutomationRequest.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MarketingAutomationRequest> marketingAutomationKafkaListenerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, MarketingAutomationRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(marketingAutomationRequestConsumerFactory());
        return  factory;
    }

}

