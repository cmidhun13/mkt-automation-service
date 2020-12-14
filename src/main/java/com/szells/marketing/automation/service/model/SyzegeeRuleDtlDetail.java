package com.szells.marketing.automation.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ram Prasad
 */
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyzegeeRuleDtlDetail implements Serializable {

    private long ruleDetailId;
    private long ruleId;
    private String ruleDetailCode;
    private String ruleDetailValue;
    private Boolean isActive;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;


}
