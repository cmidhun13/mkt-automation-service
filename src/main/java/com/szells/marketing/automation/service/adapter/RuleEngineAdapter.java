package com.szells.marketing.automation.service.adapter;


import com.google.common.base.Strings;
import com.szells.marketing.automation.service.model.RuleDetails;
import com.szells.marketing.automation.service.model.RuleResponseDetails;
import com.szells.marketing.automation.service.model.CustomerRule;
import com.szells.marketing.automation.service.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RuleEngineAdapter extends Util {

    private RestTemplate restTemplate = new RestTemplate();
    private RuleResponseDetails responseDetails;

    @Value("${url.ruleEngine}")
    private String ruleEngineURL;

    public RuleResponseDetails createProject(CustomerRule ruleProjectDetail) {
        responseDetails = restTemplate.postForObject(
                ruleEngineURL + "/createproject", ruleProjectDetail,
                RuleResponseDetails.class);
        return responseDetails;
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
