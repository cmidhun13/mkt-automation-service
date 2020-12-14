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
public class CustomerRule implements Serializable {

    private long projectId;
    private Long customerId;
    private String projectCode;
    private String projectName;
    private String projectDesc;
    private String defaultRuleName;
    private Boolean isActive;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private RuleDetails ruleDetails;


}
