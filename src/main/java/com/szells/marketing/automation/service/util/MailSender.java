package com.szells.marketing.automation.service.util;

import com.szells.marketing.automation.service.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Sagar
 */
@Slf4j
@Component
public class MailSender {


    String FROM = "dev.worthy1@gmail.com";
    String FROMNAME = "Szells";
    String HOST = "email-smtp.us-east-1.amazonaws.com";
    String SMTP_USERNAME = "AKIA4DYGT4Q7F7QPCF3Y";
    String SMTP_PASSWORD = "BMz+dvWfVQXLtrXh8vG9aL98ecIsnkGQhj4Lb4DUGEaP";
    int PORT = 587;
    String SUBJECT = "Welcome Szells Platform !!";

    public boolean emailTemplateInfo(String template, String email) throws IOException {
        log.info("Initiate emailTemplateConfig in CommunicationConfig");
        boolean isSuccess = false;
        Properties props = new Properties();

        try {
            InputStream is = CommunicationConfig.class.getResourceAsStream("/mail_settings.properties");
            props.load(is);
            // Create a Session object to represent a mail session with the specified properties.
            Session session = Session.getDefaultInstance(props);
            // Create a message with the specified information.
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject(SUBJECT);
            msg.setContent(template, "text/html; charset=utf-8");

            // Create a transport.
            Transport transport = session.getTransport();
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            isSuccess = true;
            log.info("Email sent!");
        } catch (MessagingException | IOException e) {
            log.info("error in sending email ");
            log.error("stack trace>>  ", e);
           
			throw new  GenericException(false,HttpStatus.BAD_REQUEST.value(),"Invalid Payload","Exception while sending email","{Reason=Email Id is not validated}");
        }
        log.info("end of emailTemplateConfig CommunicationConfig");
        return isSuccess;
    }
}

