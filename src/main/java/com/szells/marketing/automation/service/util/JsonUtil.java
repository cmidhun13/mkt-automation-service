package com.szells.marketing.automation.service.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import com.szells.marketing.automation.service.events.MarketingAutomationCommunicationEvent;
import com.szells.marketing.automation.service.events.MarketingAutomationInstanceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Sagar
 */
@Slf4j
@Component
public class JsonUtil {
    public JsonNode stringToJsonNode(String value) {
        try {
            return new ObjectMapper().readTree(value);
        } catch (Exception e) {
            return null;
        }
    }

    public String objectToString(Object value) {
        try {
            return new ObjectMapper().writeValueAsString(value);
        } catch (Exception e) {
            return null;
        }
    }

    public MarketingAutomationInstanceEvent getMarketingAutomationInstanceEventJson(String event) {
        Gson gson = new Gson();
        MarketingAutomationInstanceEvent marketingAutomationInstanceEvent = gson.fromJson(event, MarketingAutomationInstanceEvent.class);
        return marketingAutomationInstanceEvent;
    }


    public MarketingAutomationCommunicationEvent getMarketingAutomationCommunicationEventJson(String event) {
        Gson gson = new Gson();
        MarketingAutomationCommunicationEvent marketingAutomationCommunicationEvent = gson.fromJson(event, MarketingAutomationCommunicationEvent.class);
        return marketingAutomationCommunicationEvent;
    }

    public JsonNode objectToJsonNode(Object object) {
        return stringToJsonNode(objectToString(object));
    }

    //get the customer details from inbox and convert to into CustomerCreateDetail domain class
   /* public CustomerCreateDetail getCreateDetailfromJson(CustomerInbox customerCreateDetail) {
        Gson gson = new Gson();
        CustomerCreateDetail createDetail = gson.fromJson(customerCreateDetail.getDetails(), CustomerCreateDetail.class);
        return createDetail;
    }*/

  /*  //get the CustomerEvent from Listener and convert to into CustomerEvent domain class
    public CustomerEvent getCustomerEventFromJson(String event) {
        Gson gson = new Gson();
        CustomerEvent customerEvent = gson.fromJson(event, CustomerEvent.class);
        return customerEvent;
    }

    //get the CustomerRuleEvent from Listener and convert to into CustomerRuleEvent domain class
    public CustomerRuleEvent getCustomerRuleEventFromJson(String event) {
        Gson gson = new Gson();
        CustomerRuleEvent customerRuleEvent = gson.fromJson(event, CustomerRuleEvent.class);
        return customerRuleEvent;
    }

    //get the CustomerTemplateEvent from Listener and convert to into CustomerTemplateEvent domain class
    public CustomerTemplateEvent getCustomerTemplateEventFromJson(String event) {
        Gson gson = new Gson();
        CustomerTemplateEvent customerTemplateEvent = gson.fromJson(event, CustomerTemplateEvent.class);
        return customerTemplateEvent;
    }*/

}
