package com.szells.marketing.automation.service.response;

public class JsonResponse {

    private int code;
    private String message;
    private String description;
    private String status;

    public JsonResponse(int responseCode, String message) {
        this.code = responseCode;
        this.message = message;
    }

    public JsonResponse() {
    }

    /*public int getresponseCode() {
        return responseCode;
    }

    public void setresponseCode(int responseCode) {
        this.responseCode = responseCode;
    }*/

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseCode() {
        return code;
    }

    public void setResponseCode(int responseCode) {
        this.code = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponseCode_item() {
        return status;
    }

    public void setResponseCode_item(String responseCode_item) {
        this.status = responseCode_item;
    }

}