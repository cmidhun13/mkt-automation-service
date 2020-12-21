package com.szells.marketing.automation.service.adapter;



import com.google.common.base.Strings;
import com.szells.marketing.automation.service.constants.Constants;
import com.szells.marketing.automation.service.events.MarketingAutomationCommunicationEvent;
import com.szells.marketing.automation.service.events.MarketingAutomationEvent;
import com.szells.marketing.automation.service.events.MarketingAutomationInstanceEvent;

import com.szells.marketing.automation.service.model.ContactResult;
import com.szells.marketing.automation.service.service.MessageProducer;
import com.szells.marketing.automation.service.util.CommunicationConfig;

import com.szells.marketing.automation.service.util.JsonUtil;
import com.szells.marketing.automation.service.util.Log;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.json.JsonObject;

/**
 * @author Selva
 */
@Slf4j
@Component
public class MauticCommunicationAdaptor {

    @Autowired
    private CommunicationConfig communicationConfig;
    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private JsonUtil util;
    @Value("${automation.dbUser}")
    private String dbUser;
    @Value("${automation.dbHost}")
    private String dbHost;
    @Value("${automation.dbPassword}")
    private String dbPassword;
    @Value("${automation.adminUsername}")
    private String adminUsername;
    @Value("${automation.adminEmail}")
    private String adminEmail;
    @Value("${automation.adminFirstname}")
    private String adminFirstname;
    @Value("${automation.adminLastname}")
    private String adminLastname;

    @Value("${automation.siteUrl}")
    public  String SITE_URL;

    public String createMauticInstance(MarketingAutomationInstanceEvent marketingAutomationInstanceEvent) {
        Log.i("Initiate sendCustomerActivationEmail in CommunicationConnector" + marketingAutomationInstanceEvent.getCorrelationId());
        String result = null;
        if(!Strings.isNullOrEmpty(marketingAutomationInstanceEvent.getCusOrgName())){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
            map.add("site_url", "https://"+marketingAutomationInstanceEvent.getCusOrgName()+".sawa.rw");
            map.add("db_host", dbHost);
            map.add("db_name", marketingAutomationInstanceEvent.getCusOrgName());
            map.add("db_user", dbUser);
            map.add("db_password", dbPassword);
            map.add("admin_username", marketingAutomationInstanceEvent.getCustomerUserName());
            map.add("admin_email", marketingAutomationInstanceEvent.getEmail());
            map.add("admin_firstname", marketingAutomationInstanceEvent.getCustomerFirstName());
            map.add("admin_lastname", marketingAutomationInstanceEvent.getCustomerLastName());
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
            headers.setBasicAuth("admin","123456");
            result = RuleEngineAdapter.callPostRestAPI(SITE_URL, headers, map); // TODO will optimize the util later.
            System.out.println("correlationId: "+ "A new marking instance has been created \n"+ result);
            Log.i("This is response on creating marketing tool: "+result);
            MarketingAutomationEvent marketingAutomationEvent = MarketingAutomationEvent.builder()
                    .customerEmail(marketingAutomationInstanceEvent.getEmail())
                    .customerOrganizationName(marketingAutomationInstanceEvent.getCusOrgName())
                    .customerUserName(marketingAutomationInstanceEvent.getCustomerUserName())
                    .customerFirstName(marketingAutomationInstanceEvent.getCustomerFirstName())
                    .customerLastName(marketingAutomationInstanceEvent.getCustomerLastName())
                    .customerId(marketingAutomationInstanceEvent.getCustomerId()).build();
            String response = util.objectToString(marketingAutomationEvent);
            result = response;
            Log.i("This is response on creating marketing tool: "+result);
            messageProducer.send(Constants.MARKETING_AUTOMATION_CREATED, response); // TO DO move to Service classs
            Log.i("Published to kafka success topic");
        }
        return result;
    }

/*
    public String sendEmail(MarketingAutomationCommunicationEvent marketingAutomationCommunicationEvent) {
        Log.i("Initiate sendEmail in Mautic Adapter" + marketingAutomationCommunicationEvent.getCorrelationId());
        String url = "https://cmr.sawa.rw/emails/"+"templateId"+"/contact/"+"contactId"+"/send";

        // Need to know whether email i created emailid -

        // if the contact exist or || provide the contact id
        //I f not exist - i will create the contact
        //
        // if the emaildid - just end create the contact
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("usecode", json.getString("usecode"));
        map.add("singuplink", json.getString("singuplink"));
        String result = RuleEngineAdapter.callPostRestAPI(url, headers, map);
        Log.i("This is response on sending email: "+result);
        // messageProducer.send(result, Constants.EMAIl_CREATED);
    }
*/
    public String sendEmailforTemplate(MarketingAutomationCommunicationEvent marketingAutomationCommunicationEvent){
        String url = "https://crm.sawa.rw/emails/"+marketingAutomationCommunicationEvent.getEmail()+"/contact/"+marketingAutomationCommunicationEvent.getContactId()+"/send";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("usecode", "mergefield11");
        map.add("usecode", "mergefield11");
        map.add("singuplink", "mergefield2");
        map.add("product_image", "mergefield2");
        map.add("product_title", "mergefield2");
        String result = RuleEngineAdapter.callPostRestAPI(url, headers, map);
        Log.i("This is response on sending email: "+result);
       return result;
    }

    public String createContact(MarketingAutomationCommunicationEvent marketingAutomationCommunicationEvent) {
        Log.i("Initiate sendEmail in Mautic Adapter" + marketingAutomationCommunicationEvent.getCorrelationId());
        String url = "https://crm.sawa.rw/api/contacts/new";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("admin","123456");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        // Get the map value paramter and replace it
        map.add("firstname", "selva");
        map.add("lastname", "sakthivel");
        map.add("email", "selvagaprofile@gmail.com");
        map.add("overwriteWithBlank", "true");
        map.add("ipAddress", "127.0.0.1");
        String result = RuleEngineAdapter.callPostRestAPI(url, headers, map);

        Log.i("This is response on sending email: "+result);
        // messageProducer.send(result, Constants.EMAIl_CREATED);
        return result;
    }



  public ContactResult searchContactByEmail (MarketingAutomationCommunicationEvent marketingAutomationCommunicationEvent){
      String url = "https://crm.sawa.rw/api/contacts/?search=";
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.setBasicAuth("admin","123456");
      String result = RuleEngineAdapter.callGetRestAPI(headers,url,marketingAutomationCommunicationEvent.getEmail());
      Log.i("This is response on sending email: "+result);
      JSONObject json = new JSONObject(result);
      String count = json.getString("total");
      int totalCount = Integer.parseInt(count);
      boolean contactExist = false;
          if(totalCount>0){
              contactExist = true;
          }
      ContactResult contactResult = ContactResult.builder().
              contactCount(totalCount).
              contactExists(contactExist).contactId(null).build();
          return contactResult;
    }

}
