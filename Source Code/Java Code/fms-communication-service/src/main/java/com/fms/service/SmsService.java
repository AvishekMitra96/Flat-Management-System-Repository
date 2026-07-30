package com.fms.service;

import org.springframework.stereotype.Service;

@Service
public class SmsService {

    // Using dummy AWS SNS integration for now
    public void sendSms(String phone, String message) {
        System.out.println("--- SMS SENT VIA AWS SNS ---");
        System.out.println("Phone: " + phone);
        System.out.println("Message: " + message);
        System.out.println("----------------------------");
    }
}
