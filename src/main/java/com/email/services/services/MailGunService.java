package com.email.services.services;

import com.email.services.configurations.MailGunConfigs;
import com.email.services.model.User;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailGunService {
    @Autowired
    private MailGunConfigs mailGunConfigs;

    public  JsonNode sendMailGunMail(User user) throws UnirestException {
        try{
            System.out.println("Domain Name  "+mailGunConfigs.getDomainName() +"  API Key "+mailGunConfigs.getApiKey());

            HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + mailGunConfigs.getDomainName()+ "/messages")
                    .basicAuth("api", mailGunConfigs.getApiKey())
                    .queryString("from", "DynamicLabz luckylalgupta@gmail.com")
                    .queryString("to", user.getUserEmailId())
                    .queryString("subject", user.getSubject())
                    .queryString("text", user.getMessage())
                    .asJson();
            return request.getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
