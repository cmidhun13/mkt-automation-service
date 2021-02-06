package com.szells.marketing.automation.service.util;

import com.google.common.base.Strings;
import com.szells.marketing.automation.service.events.MarketingCreatedEvent;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class PayloadTokenizerUtil {

    public static String getStatus(boolean b){
        String status = null;
        if(!Strings.isNullOrEmpty(String.valueOf(b))){
            List<String> resultList = getTokensWithCollection(String.valueOf(b));
            status= resultList.stream().filter(p->p=="true").findAny().orElse("false");
            if(status=="false"){
                status = "error";
            }
           System.out.println("status value "+ status);

        }
        return status;
    }
    public static List<String> getTokensWithCollection(String str) {
        return Collections.list(new StringTokenizer(str, ":")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }
}
