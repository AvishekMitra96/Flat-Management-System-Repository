package com.fms.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // Using dummy AWS SES integration for now
    public void sendEmail(String to, String subject, String text) {
        System.out.println("--- EMAIL SENT VIA AWS SES ---");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + text);
        System.out.println("------------------------------");
    }
}
