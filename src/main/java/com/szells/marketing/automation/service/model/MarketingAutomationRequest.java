package com.szells.marketing.automation.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketingAutomationRequest {
    private String customerUserName;
    private String customerEmail;
    private String customerOrganizationName;
    private String customerId;
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

}
