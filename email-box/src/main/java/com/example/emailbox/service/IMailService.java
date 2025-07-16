package com.example.emailbox.service;

import com.example.emailbox.entity.MailConfig;

public interface IMailService {
    MailConfig getConfig();
    void updateConfig(MailConfig config);
}
