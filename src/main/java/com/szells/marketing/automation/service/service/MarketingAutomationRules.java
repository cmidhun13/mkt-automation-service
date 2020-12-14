package com.szells.marketing.automation.service.service;

public interface MarketingAutomationRules<I, O> {
    O process(I input);
}
