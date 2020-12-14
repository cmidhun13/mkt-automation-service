/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.szells.marketing.automation.service.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Strings;
import com.szells.marketing.automation.service.adapter.RuleEngineAdapter;
import com.szells.marketing.automation.service.exception.CustomerRuntimeException;
import com.szells.marketing.automation.service.model.RuleDetails;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Sagar
 */
@Slf4j
@Component
public class CommunicationConfig {

    public boolean emailTemplateInfo(String template,String email) {
        log.info("Initiate emailTemplateConfig in CommunicationConfig");
        boolean isSuccess = false;
        Properties props = new Properties();
        String senderEmail = "girishathanikar54@gmail.com";
        String senderPassword = "7259667143$$##";
        try {
            InputStream is = CommunicationConfig.class.getResourceAsStream("/mail_settings.properties");
            props.load(is);
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));//provider defalut account
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email)); //To reciever mail id
            message.setSubject("Welcome to Szells Platform");
            message.setContent(template, "text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart(template);
            Transport.send(message);
            isSuccess = true;
        } catch (MessagingException | IOException e) {
            log.info("error in sending email ");
            log.error("stack trace>>  ",e);

            throw new CustomerRuntimeException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
                    "Exception while sending email");
        }
        log.info("end of emailTemplateConfig CommunicationConfig");
        return isSuccess;
    }



    public static String getRuleDetailsByCustomerId(String customerId, String customerRuleEngineId){
        RuleDetails ruleDetails = null;
        String automationTool = null;
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = new RestTemplate();
            String ruleEngineUrl = "http://localhost:8090/syzegee/v1/ruleengine/runrule";
            String onj = "{\n" +
                    "    ruleName(name: \"Marketing-Automation\"){\n" +
                    "    szRuleDetailsCollection{\n" +
                    "    ruleDetailValue\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
            HttpEntity<String> request = new HttpEntity<String>(onj, headers);
            String result = restTemplate.postForObject(ruleEngineUrl, request, String.class);
            System.out.println("Jumped: "+result);
            JSONObject json = null;
            if(!Strings.isNullOrEmpty(result)){
                JSONObject jb = new JSONObject(result);
                JSONArray jsonArray;
                jsonArray = jb.getJSONObject("ruleName").getJSONArray("szRuleDetailsCollection");
//                ruleDetails.setRuleAttributeDetails(jsonArray);
//                ruleDetails.setProjectDetail(jb.getJSONObject("ruleName"));
//                ruleDetails.setRuleValueDetails();
                for(int i=0; i<jsonArray.length(); i++){
                    System.out.println(" This is the objects: "+jsonArray.getJSONObject(i));
                    json = jsonArray.getJSONObject(0);
                }
            }
            if(json.has("ruleDetailValue")){
                automationTool = json.getString("ruleDetailValue");
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            return automationTool;
        }
    }
}
