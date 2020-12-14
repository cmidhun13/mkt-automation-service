package com.szells.marketing.automation.service.model;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Selva
 */
@Builder
@Data

public class MarketingCommunicationResponse {
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
