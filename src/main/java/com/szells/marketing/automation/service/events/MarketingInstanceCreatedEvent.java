package com.szells.marketing.automation.service.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketingInstanceCreatedEvent {

    // TO DO add all the fields
    private String customerId;
    private String cusOrgName;
    private String correlationId;
    private String customerRuleEngineId;
    private String email;
    private String marketingInstanceCreatedStatus;
    private String marketingAutomationTenantUrl;


}
