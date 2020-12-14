/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.szells.marketing.automation.service.exception;

 import org.springframework.http.HttpStatus;


/**
 * @author Sagar
 */
public class CustomerServiceException extends RuntimeException {

    private HttpStatus status;
    private int code;
    private String message;

    public CustomerServiceException(Exception exception) {
        super(exception);
    }

    public CustomerServiceException(HttpStatus httpStatus, int code, String message) {
        this.status = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.status = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
