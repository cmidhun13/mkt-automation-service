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
    
    
    
    private String customerUserName;
    private String customerEmail;
    private String customerOrganizationName;
    private String customerRuleEngineId;
    
    //private String email;
    private String customerFirstName;
    private String customerLastName;
    private String customerPassWord;
    
    private String siteCode;
    private String siteName;
    private String domainName;
    private String siteType;
    private String marketingAutomationSsoUrl;
    private boolean status;
    
    @Override
    public String toString() {
    	
    	return "{"+"customerId="+customerId+",correlationId="+correlationId+",status="+status+"}";
    }
}
