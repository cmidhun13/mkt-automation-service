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
public class SyzegeeProjectRulesDetail implements Serializable {
    private long projectRuleId;
    private long projectId;
    private long ruleId;
    private String ruleValue;
    private Boolean isActive;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;


}
