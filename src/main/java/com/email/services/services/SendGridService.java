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

    public Response sendSendGridMail(User user) throws IOException {
        Email from = new Email("greedycodertech@gmail.com");
        String subject = "Welcome to Master Academy!";
        Email to = new Email(user.getUserEmailId());
        Content content = new Content("text/plain", "Dear "+user.getName()+", \n\n"+user.getMessage());
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(sendGridConfigs.getApiKey());
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("status code"+response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            return response;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
