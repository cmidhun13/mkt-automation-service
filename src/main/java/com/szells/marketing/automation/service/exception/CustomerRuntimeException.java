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

public class CustomerRuntimeException extends RuntimeException{


    private HttpStatus status;
    private int code = -1;
    private String message;
    

    public CustomerRuntimeException() {
    }

    public CustomerRuntimeException(Exception exception) {
        super(exception);
    }

    public CustomerRuntimeException(HttpStatus status, int code, String message) {
        this.code = code;
        this.status = status;
        this.message=message;
    }
    

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
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
