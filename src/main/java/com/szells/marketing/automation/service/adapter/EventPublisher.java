package com.szells.marketing.automation.service.adapter;

import com.szells.marketing.automation.service.service.MessageProducer;
import com.szells.marketing.automation.service.util.Log;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Sagar
 */
@Slf4j
@Data
@Component
public class EventPublisher {

    @Autowired
    private MessageProducer messageProducer;


    public void send(String topic, String message) {
        Log.i("Initiate to send in EventPublisher " + " - message: " + message + "  topic: " + topic  );
        messageProducer.send(topic, message);
    }

}