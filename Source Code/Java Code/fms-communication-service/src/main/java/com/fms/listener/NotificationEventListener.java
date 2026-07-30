package com.fms.listener;

import com.fms.dto.NotificationEvent;
import com.fms.service.EmailService;
import com.fms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    private final EmailService emailService;
    private final SmsService smsService;

    @KafkaListener(topics = "fms_notifications", groupId = "communication_group")
    public void handleNotificationEvent(NotificationEvent event) {
        System.out.println("Received Notification Event: " + event.getType());
        
        if (event.getRecipientEmail() != null && !event.getRecipientEmail().isEmpty()) {
            emailService.sendEmail(event.getRecipientEmail(), event.getSubject(), event.getMessage());
        }
        
        if (event.getRecipientPhone() != null && !event.getRecipientPhone().isEmpty()) {
            smsService.sendSms(event.getRecipientPhone(), event.getMessage());
        }
    }
}
