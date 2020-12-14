package com.szells.marketing.automation.service.validator;

import com.google.common.base.Strings;
import com.szells.marketing.automation.service.adapter.MauticCommunicationAdaptor;
import com.szells.marketing.automation.service.constants.Constants;
import com.szells.marketing.automation.service.exception.CustomerRuntimeException;

import com.szells.marketing.automation.service.model.MarketingAutomationRequest;
import com.szells.marketing.automation.service.model.MarketingCommunicationRequest;
import com.szells.marketing.automation.service.response.JsonResponse;
import com.szells.marketing.automation.service.service.MessageProducer;
import com.szells.marketing.automation.service.util.Log;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MarketingAutomationValidator {

	@Autowired
	private MauticCommunicationAdaptor mauticCommunicationAdaptor;

	@Autowired
	private MessageProducer messageProducer;

	public boolean validateEntity(String fieldName, Object entity) {
		if (entity != null) {
			//log.info("field value is:" + entity);
			return true;
		} else {
			throw new CustomerRuntimeException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), fieldName + " is not present " +
					"with given id");
		}
	}


	public boolean validateMarketingAutomationRequest(MarketingAutomationRequest marketingAutomationRequest,String correlationId) {

		if (marketingAutomationRequest != null && !Strings.isNullOrEmpty(correlationId)) {
			if(!Strings.isNullOrEmpty(marketingAutomationRequest.getCustomerOrganizationName())&&
					!Strings.isNullOrEmpty(marketingAutomationRequest.getCustomerEmail()) ){
				return true;
			}
		}

		return false;

	}

	public boolean validateMarketingCommunicationRequest(MarketingCommunicationRequest marketingCommunicationRequest, String correlationId) {

		if (marketingCommunicationRequest != null && !Strings.isNullOrEmpty(correlationId)) {
			if(!Strings.isNullOrEmpty(marketingCommunicationRequest.getCustomerOrganizationName())&&
					!Strings.isNullOrEmpty(marketingCommunicationRequest.getCustomerEmail()) ){
				return true;
			}
		}

		return false;

	}


	public String constructSiteUrl(String siteUrl, String userName){
		if(!Strings.isNullOrEmpty(siteUrl)){
			if(siteUrl.contains("http:")){

			}
			return siteUrl;
		}
		else{ return userName;
		}
	}

}
