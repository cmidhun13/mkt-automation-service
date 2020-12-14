package com.szells.marketing.automation.service.service;

import com.szells.marketing.automation.service.util.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



/**
 * @author Sagar
 */
@Service
@Slf4j
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String message) {
        Log.i("Topic: "+topic);
        Log.i(String.format("sending data='{}'", message));
        kafkaTemplate.send(topic, message);
        }
}
