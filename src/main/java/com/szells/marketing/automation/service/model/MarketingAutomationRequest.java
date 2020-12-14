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

}
