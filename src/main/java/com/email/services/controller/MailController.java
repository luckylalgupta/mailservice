package com.email.services.controller;

import com.email.services.model.User;
import com.email.services.services.SendGridService;
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

    @PostMapping(value="/")
    public boolean getEmail(@RequestBody User user) throws IOException {
        return sendGridService.sendWelcomeEmail(user);
    }
}


