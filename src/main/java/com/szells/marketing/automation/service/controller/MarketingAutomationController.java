/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.szells.marketing.automation.service.controller;


import com.szells.marketing.automation.service.model.MarketingAutomationRequest;
import com.szells.marketing.automation.service.model.MarketingAutomationResponse;
import com.szells.marketing.automation.service.exception.CustomerServiceException;
import com.szells.marketing.automation.service.model.MarketingCommunicationRequest;
import com.szells.marketing.automation.service.model.MarketingCommunicationResponse;
import com.szells.marketing.automation.service.response.GenericResponse;
import com.szells.marketing.automation.service.response.JsonResponse;
import com.szells.marketing.automation.service.service.MarketingAutomationService;

import com.szells.marketing.automation.service.util.CommunicationConfig;
import com.szells.marketing.automation.service.util.CorrelationIdUtil;
import com.szells.marketing.automation.service.util.Log;
import com.szells.marketing.automation.service.util.Util;
import com.szells.marketing.automation.service.validator.MarketingAutomationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Selva
 */
@Slf4j
@RestController
@RequestMapping("/v1/marketing")
public class MarketingAutomationController {

	@Autowired
	MarketingAutomationService marketingAutomationService;
	@Autowired
	MarketingAutomationValidator validator;

	@PostMapping(path = "/instance")
	public ResponseEntity<GenericResponse> createMarketingAutomationInstance(@RequestHeader(value = "correlationId",
			required = false, defaultValue = "") String correlationId, @RequestBody MarketingAutomationRequest marketingAutomationRequest)
			throws CustomerServiceException, IOException {

		log.info("Initiate activationPostRequest in controller : " + " - CorrelationId: " + correlationId);
		MarketingAutomationResponse marketingAutomationResponse = null;
		GenericResponse response =null;
		boolean validationFalg = validator.validateMarketingAutomationRequest(marketingAutomationRequest,correlationId);
		marketingAutomationResponse = marketingAutomationService.createMarketingAutomationInstanceEvent(marketingAutomationRequest, correlationId);
		// TO DO we have to simplify this logic
		if(marketingAutomationResponse ==null) {
			response =new GenericResponse(true,HttpStatus.OK.value(),"Customer creation failed ","Customer creation failed","");
		}else {
			response =new GenericResponse(true,HttpStatus.OK.value(),"Customer created ","Customer creation completed",marketingAutomationResponse);
		}
		ResponseEntity responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		log.info("End of activationPostRequest in controller: " + " - CorrelationId: " + correlationId);
		return responseEntity;
	}
	@PostMapping(path = "/email")
	public ResponseEntity<GenericResponse> createMarketingAutomationInstance(@RequestHeader(value = "correlationId",
			required = false, defaultValue = "") String correlationId, @RequestBody MarketingCommunicationRequest marketingCommunicationRequest)
			throws CustomerServiceException, IOException {
		log.info("Initiate activationPostRequest in controller : " + " - CorrelationId: " + correlationId);
		MarketingCommunicationResponse marketingCommunicationResponse = null;
		GenericResponse response =null;
		boolean validationFalg = validator.validateMarketingCommunicationRequest(marketingCommunicationRequest,correlationId);
		marketingCommunicationResponse = marketingAutomationService.createMarketingCommunicationEvent(marketingCommunicationRequest, correlationId);
		// TO DO we have to simplify this logic
		if(marketingCommunicationResponse ==null) {
			response =new GenericResponse(true,HttpStatus.OK.value(),"Customer creation failed ","Customer creation failed","");
		}else {
			response =new GenericResponse(true,HttpStatus.OK.value(),"Customer created ","Customer creation completed",marketingCommunicationResponse);
		}
		ResponseEntity responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		log.info("End of activationPostRequest in controller: " + " - CorrelationId: " + correlationId);
		return responseEntity;
	}




}
