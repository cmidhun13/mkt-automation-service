package com.szells.marketing.automation.service.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResult {

    private String contactId;
    private  int contactCount;
    private boolean contactExists;
}
