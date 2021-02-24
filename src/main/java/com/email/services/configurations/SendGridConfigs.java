package com.email.services.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sendgrid")
public class SendGridConfigs {
    private String supportApiKey;

    public String getSupportApiKey() {
        return supportApiKey;
    }

    public void setSupportApiKey(String supportApiKey) {
        this.supportApiKey = supportApiKey;
    }
}