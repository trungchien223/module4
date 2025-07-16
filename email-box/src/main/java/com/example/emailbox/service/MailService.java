package com.example.emailbox.service;

import com.example.emailbox.entity.MailConfig;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService{
    private MailConfig config = new MailConfig();
    @Override
    public MailConfig getConfig() {
        return config;
    }
    @Override
    public void updateConfig(MailConfig config) {
        this.config = config;
    }
}
