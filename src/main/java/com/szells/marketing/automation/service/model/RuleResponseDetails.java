package com.szells.marketing.automation.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

/**
 * Ram Prasad
 */
@Builder
@Data
@AllArgsConstructor
public class RuleResponseDetails {
    private long projectId;
    private String correlationId;
    private String message;

    @Tolerate
    public RuleResponseDetails(){}
}
