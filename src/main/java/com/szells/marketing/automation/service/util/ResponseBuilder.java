//package com.syzegee.customer.events.util;
//
//import com.syzegee.member.events.domain.response.ReturnDetail;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//import com.syzegee.member.events.util.constant.*;
//
//import lombok.extern.slf4j.Slf4j;
//
//

/**
 * @author Sagar
 */
//@Component
//@Slf4j
//public class ResponseBuilder {
//
//    public ReturnDetail returnDetail(JSONObject result) {
//        return ReturnDetail.builder().code(result.getInt(Constants.CODE)).messageDescription(result.getString(Constants.TITLE))
//                        .messageSource(Constants.MEMBER_MEMBERSHIPSUBCRIPTION).build();
//    }
//
//    public ReturnDetail successReturnDetail(int code, String messageDescription, String messageSource) {
//        return ReturnDetail.builder().code(code).messageDescription(messageDescription).messageSource(messageSource).build();
//    }
//
//    public ReturnDetail errorReturnDetail(int code, String errorDetails, String messageSource) {
//        return ReturnDetail.builder().code(code).errorDetails(errorDetails).messageSource(messageSource).build();
//    }
//}
//
