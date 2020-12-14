package com.szells.marketing.automation.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.List;

/**
 * Ram Prasad
 */
@Data
@Builder
@AllArgsConstructor
public class RuleDetails implements Serializable {

    private ProjectDetail projectDetail;
    private SyzegeeRuleDetail ruleNameDetails;
    private List<SyzegeeRuleDtlDetail> ruleAttributeDetails;
    private List<SyzegeeProjectRulesDetail> ruleValueDetails;
    @Tolerate
    public RuleDetails() {
    }
}
