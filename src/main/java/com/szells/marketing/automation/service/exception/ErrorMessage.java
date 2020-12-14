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
public class ErrorMessage {

    private HttpStatus status;
    private int code;
    private String message;

    public ErrorMessage() {
    }


    public ErrorMessage(HttpStatus status, int code, String message) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ErrorMessage(HttpStatus status, int code) {
        super();
        this.status = status;
        this.code = code;
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
