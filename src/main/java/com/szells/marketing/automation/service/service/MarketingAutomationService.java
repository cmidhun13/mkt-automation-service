/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.szells.marketing.automation.service.service;


import com.google.common.base.Strings;
import com.szells.marketing.automation.service.adapter.EventPublisher;
import com.szells.marketing.automation.service.adapter.RuleEngineAdapter;
import com.szells.marketing.automation.service.constants.Constants;
import com.szells.marketing.automation.service.events.MarketingAutomationCommunicationEvent;
import com.szells.marketing.automation.service.events.MarketingAutomationInstanceEvent;
import com.szells.marketing.automation.service.adapter.MauticCommunicationAdaptor;
import com.szells.marketing.automation.service.events.MarketingInstanceCreatedEvent;
import com.szells.marketing.automation.service.model.*;

import com.szells.marketing.automation.service.response.JsonResponse;
import com.szells.marketing.automation.service.util.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * @author Selva
 */
@Service
@Slf4j
public class MarketingAutomationService {
    @Autowired
    private EventPublisher publisher;
    @Autowired
    private JsonUtil util;
    @Autowired
    private MauticCommunicationAdaptor mauticCommunicationAdaptor;
    @Autowired
    private EventPublisher eventPublisher;
    @Autowired
    private RuleEngineAdapter ruleEngineAdapter;

    public MarketingAutomationResponse createMarketingAutomationInstance(MarketingAutomationInstanceEvent marketingAutomationInstanceEvent) throws IOException,Exception {
        Log.i("Initiate to create Markting instance in service " + " - CorrelationId: " + marketingAutomationInstanceEvent.getCorrelationId());
        String marketingAutomationProvider  = ruleEngineAdapter.getRuleDetailsByCustomerId(marketingAutomationInstanceEvent.getCustomerId(), marketingAutomationInstanceEvent.getCustomerRuleEngineId());
        if(marketingAutomationProvider.equalsIgnoreCase("mautic")){
            String response =
                    mauticCommunicationAdaptor.createMauticInstance(marketingAutomationInstanceEvent);
            MarketingAutomationResponse marketingAutomationResponse = MarketingAutomationResponse.builder().customerId(marketingAutomationInstanceEvent.getCustomerId())
                    .correlationId(marketingAutomationInstanceEvent.getCorrelationId())
                    .emailId(marketingAutomationInstanceEvent.getEmail())
                    .build();
            Log.i("End of createMarketingAutomationInstance creation " + " - CorrelationId: " + marketingAutomationInstanceEvent.getCorrelationId());
            MarketingInstanceCreatedEvent marketingInstanceCreatedEvent =
                    createMarketingAutomationCreatedEvent(response, marketingAutomationInstanceEvent.getCorrelationId(),marketingAutomationInstanceEvent.getCustomerId());
            marketingInstanceCreatedEvent.setMarketingAutomationTenantUrl("https://"+marketingAutomationInstanceEvent.getCusOrgName()+".sawa.rw");
            String jsonMarketingCreatedEvent = util.objectToString(marketingInstanceCreatedEvent);
            System.out.println("Publishing the event for marketing created : "+ jsonMarketingCreatedEvent);
            eventPublisher.send(Constants.CUSTOMER_CREATED, jsonMarketingCreatedEvent);
            // TO DO publish another Event to Customer construct same way as marketing but you can do it later.
            return marketingAutomationResponse;
        }
        else if(marketingAutomationProvider.equalsIgnoreCase("exponea")){
            //TODO call exponea create Instance method.
        }
        return null;
    }

    public MarketingAutomationResponse sendCommunication(MarketingAutomationCommunicationEvent marketingAutomationCommunicationEvent) throws IOException,Exception {
        Log.i("Initiate to create Markting instance in service " + " - CorrelationId: " + marketingAutomationCommunicationEvent.getCorrelationId());
        String marketingAutomationProvider  = ruleEngineAdapter.getRuleDetailsByCustomerId(marketingAutomationCommunicationEvent.getCustomerId(), marketingAutomationCommunicationEvent.getCustomerRuleEngineId());
        if(marketingAutomationProvider.equalsIgnoreCase("mautic")){
            ContactResult contactExists = mauticCommunicationAdaptor.searchContactByEmail(marketingAutomationCommunicationEvent);
            if(!contactExists.isContactExists()) {
                mauticCommunicationAdaptor.createContact(marketingAutomationCommunicationEvent); 
            }
            //TODO Get the rule engine template
            String response =
                    mauticCommunicationAdaptor.sendEmailforTemplate(null);
            MarketingAutomationResponse marketingAutomationResponse = MarketingAutomationResponse.builder().customerId(marketingAutomationCommunicationEvent.getCustomerId())
                    .correlationId(marketingAutomationCommunicationEvent.getCorrelationId())
                    .emailId(marketingAutomationCommunicationEvent.getEmail())
                    .build();
            // TO DO publish another Event to Customer construct same way as marketing but you can do it later.
            return marketingAutomationResponse;
        }
        else if(marketingAutomationProvider.equalsIgnoreCase("exponea")){
            //TODO call exponea create Instance method.
        }
        return null;
    }

    public MarketingAutomationResponse createMarketingAutomationInstanceEvent(MarketingAutomationRequest marketingAutomationRequest, String correlationId) throws IOException {
        log.info("Initiate createActivation in service " + " - CorrelationId: " + correlationId);
        MarketingAutomationResponse marketingAutomationResponse = MarketingAutomationResponse.builder().customerId(marketingAutomationRequest.getCustomerId())
                .correlationId(correlationId)
                .customerId(marketingAutomationRequest.getCustomerId())
                .message("Marketing Automation Request has been submitted")
                .build();
        MarketingAutomationInstanceEvent marketingAutomationInstanceEvent = MarketingAutomationInstanceEvent.builder()
                .correlationId(correlationId)
                .customerId(marketingAutomationRequest.getCustomerId())
                .cusOrgName(marketingAutomationRequest.getCustomerOrganizationName())
                .customerRuleEngineId(marketingAutomationRequest.getCustomerRuleEngineId())
                .email(marketingAutomationRequest.getCustomerEmail())
                .build();
        String jsonMarketingEvent = util.objectToString(marketingAutomationInstanceEvent);
        System.out.println("jsonMarketingEvent : "+ jsonMarketingEvent);
        eventPublisher.send(Constants.MARKETING_AUTOMATION,jsonMarketingEvent);
        log.info("End of createActivation in service " + " - CorrelationId: " + correlationId);

        return marketingAutomationResponse;
    }

    public MarketingCommunicationResponse createMarketingCommunicationEvent(MarketingCommunicationRequest marketingCommunicationRequest, String correlationId) throws IOException {
        log.info("Initiate createActivation in service " + " - CorrelationId: " + correlationId);
        //TODO Validate the Rule engine
        MarketingCommunicationResponse marketingCommunicationResponse = MarketingCommunicationResponse.builder().customerId(marketingCommunicationRequest.getCustomerId())
                .correlationId(correlationId)
                .customerId(marketingCommunicationRequest.getCustomerId())
                .message("Marketing Automation Request has been submitted")
                .build();
        MarketingAutomationCommunicationEvent marketingAutomationCommunicationEvent = MarketingAutomationCommunicationEvent.builder()
                .correlationId(correlationId)
                .customerId(marketingCommunicationRequest.getCustomerId())
                .cusOrgName(marketingCommunicationRequest.getCustomerOrganizationName())
                .customerRuleEngineId(marketingCommunicationRequest.getCustomerRuleEngineId())
                .email(marketingCommunicationRequest.getCustomerEmail())
                .build();
        String jsonMarketingEvent = util.objectToString(marketingAutomationCommunicationEvent);
        System.out.println("jsonMarketingEvent : "+ jsonMarketingEvent);
        eventPublisher.send(Constants.SEND_EMAIL,jsonMarketingEvent);
        log.info("End of createActivation in service " + " - CorrelationId: " + correlationId);

        return marketingCommunicationResponse;
    }

    public MarketingInstanceCreatedEvent createMarketingAutomationCreatedEvent(String payload, String correlationId,String customerId) throws Exception{
        String status = PayloadTokenizerUtil.getStatus(payload);

        return MarketingInstanceCreatedEvent.builder()
                .correlationId(correlationId)
                .customerId(customerId)
                .marketingInstanceCreatedStatus(status).build();

    }
}
