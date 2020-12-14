package com.szells.marketing.automation.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Ram Prasad
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyzegeeRuleDetail implements Serializable {
    private long ruleId;
    private String ruleCode;
    private String ruleName;
    private String ruleDesc;
    private String ruleType;
    private Boolean isActive;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;


}
