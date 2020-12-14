 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.szells.marketing.automation.service.exception;

 import com.szells.marketing.automation.service.response.GenericResponse;
 import org.springframework.http.HttpHeaders;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.http.converter.HttpMessageNotReadableException;
 import org.springframework.web.bind.annotation.ControllerAdvice;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.RestController;
 import org.springframework.web.context.request.WebRequest;
 import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


 /**
  * @author Sagar
  */
@ControllerAdvice 
@RestController 
public class MarketingAutomationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ErrorMessage(status, status.value(), "Provide valid JSON input"));
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorMessage errorMessage) {
        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "Bad Request Error Occured..!");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerServiceException.class)
    public final ResponseEntity<ErrorMessage> handleUserNotFoundException(CustomerServiceException exception,
                                                                          WebRequest request) {
       ErrorMessage errorMessage
                = new ErrorMessage(exception.getHttpStatus(), exception.getCode(),
                        exception.getMessage());
        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }
    @ExceptionHandler(CustomerRuntimeException.class)
    public final ResponseEntity<ErrorMessage> handleSyzegeeException(CustomerRuntimeException exception,
                                                                     WebRequest request) {
        ErrorMessage errorMessage
                = new ErrorMessage(exception.getStatus(), exception.getCode(),
                exception.getMessage());
        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }
    
    @ExceptionHandler(GenericException.class)
    public final GenericResponse handleSyzegeeException(GenericException exception,
                                                                     WebRequest request) {
    	GenericResponse errorMessage
                = new GenericResponse(false,exception.getStatus(),exception.getMessage(),exception.getDescription(), exception.getData()
                );
        return errorMessage;
    }

}
