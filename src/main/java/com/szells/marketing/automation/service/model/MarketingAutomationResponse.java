package com.szells.marketing.automation.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Selva
 */
@Builder
@Data


public class MarketingAutomationResponse {

    private String customerId;
    private String emailId;
    private String correlationId;

    // To Do add all the response fields needed
    private String message;
    @Override
    public String toString() {
    	
    	return "{"+"customerId="+customerId+",correlationId="+correlationId+"}";
    }
}
