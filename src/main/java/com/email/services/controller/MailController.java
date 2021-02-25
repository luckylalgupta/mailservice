package com.email.services.controller;

import com.email.services.model.User;
import com.email.services.services.MailGunService;
import com.email.services.services.SendGridService;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping(value = "/api")
public class MailController {
    @Autowired
    private SendGridService sendGridService;

    @Autowired
    private MailGunService mailGunService;

    @PostMapping(value="/sendGrid")
    public Response sendEmailBySendGrid(@RequestBody User user) throws IOException ,UnirestException{
        Response response = sendGridService.sendSendGridMail(user);
        if(response.getStatusCode()!=202 ||response.getStatusCode()!=200){
            sendEmailBySendMailGun(user);
        }
        return response;
    }

    @PostMapping(value="/mailGun")
    public JsonNode sendEmailBySendMailGun(@RequestBody User user) throws UnirestException {

        JsonNode node= mailGunService.sendMailGunMail(user);

        return node;
    }
}


