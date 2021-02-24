package com.email.services.services;

import com.email.services.configurations.SendGridConfigs;
import com.email.services.model.User;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridService {
    @Autowired
    private SendGridConfigs sendGridConfigs;

    public boolean sendWelcomeEmail(User user) throws IOException {
        Email from = new Email("greedycodertech@gmail.com");
        String subject = "Welcome to Master Academy!";
        Email to = new Email(user.getMail());
        Content content = new Content("text/plain", "Dear "+user.getName()+", \n\n"+"Welcome to our channel. \n\n"+"Yours truely, \n Master Academy.");
        Mail mail = new Mail(from, subject, to, content);
        System.out.println(sendGridConfigs.getSupportApiKey());
        SendGrid sg = new SendGrid(sendGridConfigs.getSupportApiKey());
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
