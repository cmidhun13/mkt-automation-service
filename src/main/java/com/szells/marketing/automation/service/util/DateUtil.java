package com.szells.marketing.automation.service.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Sagar
 */
@Slf4j
@Component
public class DateUtil {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();

    public Date getDateformat(Date dateformat) {
        Date date = null;
        try {
            final String format = sdf.format(dateformat);
            date = sdf.parse(format);
            return date;
        } catch (ParseException ex) {
            log.error("Error: " + ex);
        }
        return date;
    }

    public Date addDaysToDate(Date startDate, String termValue) {
        log.info("----------------------Start::addDaysToDate----------------------");
        Date endDate = null;
        try {
            c.setTime(sdf.parse(String.valueOf(startDate)));
            c.add(Calendar.DAY_OF_MONTH, Integer.valueOf(termValue));
            String newDate = sdf.format(c.getTime());
            endDate = sdf.parse(newDate);
        } catch (ParseException ex) {
            log.error("Error: " + ex);
        }
        log.info("----------------------End::generateAlphanumericOtp----------------------");
        return endDate;
    }

    public Date addMonthsToDate(Date startDate, String termValue) {
        log.info("----------------------Start::addMonthsToDate----------------------");
        Date endDate = null;
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            c.setTime(sdf1.parse(String.valueOf(startDate)));
            c.add(Calendar.MONTH, Integer.valueOf(termValue));
            String newDate = sdf.format(c.getTime());
            endDate = sdf.parse(newDate);
        } catch (ParseException ex) {
            log.error("Error: " + ex);
        }
        log.info("----------------------End::addMonthsToDate----------------------");
        return endDate;
    }

    public long getDifferenceForCurrentDate(long requestedTime) {
        Date currentDate = new Date();
        long currentTimeInMilliseconds = currentDate.getTime();
        long difference = currentTimeInMilliseconds - requestedTime;
        return difference;
    }

}
