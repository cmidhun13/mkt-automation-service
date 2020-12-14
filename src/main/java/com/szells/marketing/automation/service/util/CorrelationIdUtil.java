package com.szells.marketing.automation.service.util;

import com.szells.marketing.automation.service.exception.CustomerServiceException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.regex.Pattern;


/**
 * @author Sagar
 */
@Component
public class CorrelationIdUtil {


    public static String generateCorrelationId() {
        String correlationId = UUID.randomUUID().toString();
        return correlationId;
    }
    
    public static String generateActivationCode(String correlationId) {
	    String activationCode = RandomStringUtils.randomNumeric(6);
        return activationCode;
    }
    
    

    public static String generateCorrelationId(String uuid) throws CustomerServiceException {
        try {
            if (uuid.isEmpty()) {
                return generateCorrelationId();
            } else {
                if (!isUUID(uuid)) {
                    throw new CustomerServiceException(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), "correlationId must be valid UUID format");
                }
            }
        } catch (Exception e) {
			throw new CustomerServiceException(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(), "correlationId must be valid UUID format");
        }
        return uuid;
    }

    static final Pattern UUIDs = Pattern
            .compile("(?i)^[0-9a-z]{8}-?[0-9a-z]{4}-?[0-9a-z]{4}-?[0-9a-z]{4}-?[0-9a-z]{12}$");

    public static boolean isUUID(String string) {
        try {
            return UUIDs.matcher(string).matches();
        } catch (Exception ex) {
            return false;
        }
    }
    

}
