package com.szells.marketing.automation.service.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class Util {
    @Value("${automation.dbHost}")
    public  String DB_HOST;
    @Value("${automation.dbUser}")
    public  String DB_USER;
    @Value("${automation.dbPassword}")
    public  String DB_PASSWORD;
    @Value("${automation.adminUsername}")
    public  String ADMIN_USERNAME;
    @Value("${automation.adminEmail}")
    public  String ADMIN_EMAIL;
    @Value("${automation.adminFirstname}")
    public  String ADMIN_FISRTNAME;
    @Value("${automation.adminLastname}")
    public  String ADMIN_LASTNAME;
    @Value("${automation.siteUrl}")
    public  String SITE_URL;
    @Value("${url.ruleEngine}")
    private  String ruleEngineUrl;
    private  String marketingAutomationTopic = "Marketing-Automation";

    private static RestTemplate restTemplate;
    public static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper;
    }
    public static String callPostRestAPI(String url, HttpHeaders headers, MultiValueMap<String, String> map){
        restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        return restTemplate.postForObject(url, request, String.class);
    }
    public static String callGetRestAPI(HttpHeaders headers, String param1, String param2, String url){
        restTemplate = new RestTemplate();
       // HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);
        return restTemplate.getForObject(url+"/"+param1, String.class);
    }

    public static String callGetRestAPI(HttpHeaders headers, String url, String param1){
        restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);
        return restTemplate.getForObject(url+"/"+param1, String.class);
    }
}
