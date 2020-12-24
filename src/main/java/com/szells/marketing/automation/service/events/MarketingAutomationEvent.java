package com.szells.marketing.automation.service.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketingAutomationEvent {
    private String customerUserName;
    private String customerEmail;
    private String customerOrganizationName;
    private String customerId;
    private String customerFirstName;
    private String customerLastName;
}
