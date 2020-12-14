package com.szells.marketing.automation.service.listeners;


import com.szells.marketing.automation.service.events.MarketingAutomationCommunicationEvent;
import com.szells.marketing.automation.service.events.MarketingAutomationInstanceEvent;
import com.szells.marketing.automation.service.service.MarketingAutomationService;
import com.szells.marketing.automation.service.util.JsonUtil;
import com.szells.marketing.automation.service.constants.Constants;
import com.szells.marketing.automation.service.util.Log;
import com.szells.marketing.automation.service.validator.MarketingAutomationValidator;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Data
@Builder
@Slf4j
@Service
public class MarketingAutomationHandler {

    @Autowired
    MarketingAutomationValidator validator;

    @Autowired
    private MarketingAutomationService marketingAutomationService;

    @Autowired
    JsonUtil util;

    @Tolerate
    public MarketingAutomationHandler() {
    }
    @KafkaListener(topics = {Constants.CUSTOMER_CREATED,  Constants.MARKETING_AUTOMATION, Constants.SEND_EMAIL})
    public void consumeCustomerEvent(ConsumerRecord<String, Object> event) {
        try {
            Log.i("Initiate consumeCustomerEvent in MarketingAutomationHandler" + " - CorrelationId: " + event);

           if(event.topic().equalsIgnoreCase(Constants.CUSTOMER_CREATED)){
               System.out.println("Marketing Event is created " + event.value().toString());
            }
            if(event.topic().equalsIgnoreCase(Constants.MARKETING_AUTOMATION)){
                System.out.println("Inside Marketing Automation----Topic" + event.value().toString());
                MarketingAutomationInstanceEvent marketingAutomationInstanceEvent = util.getMarketingAutomationInstanceEventJson(event.value().toString());
                marketingAutomationService.createMarketingAutomationInstance(marketingAutomationInstanceEvent);
            }
            if(event.topic().equalsIgnoreCase(Constants.SEND_EMAIL)){
                System.out.println("Inside Marketing Automation----Topic" + event.value().toString());
                MarketingAutomationCommunicationEvent marketingAutomationCommunicationEvent = util.getMarketingAutomationCommunicationEventJson(event.value().toString());
                marketingAutomationService.sendCommunication(marketingAutomationCommunicationEvent);
            }
            System.out.println("End of consumeCustomerEvent in MarketingAutomationHandler");
        } catch (Exception ex){
            Log.i("In MarketingAutomationHandler Error occured "+ex+"  correlationId="+event.value().toString());
        }
    }
}
