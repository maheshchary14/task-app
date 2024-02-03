package com.example.taskapp.services;

// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class MailService {

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;
    @Value("${FROM_MAIL}")
    private String fromMail;

    public void sendMail(Map<String, String> emailInfo) throws IOException {
        Email from = new Email(fromMail);
        Email to = new Email(emailInfo.get("toMail"));
        Content content = new Content(emailInfo.get("contentType"), emailInfo.get("content"));
        Mail mail = new Mail(from, emailInfo.get("subject"), to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}