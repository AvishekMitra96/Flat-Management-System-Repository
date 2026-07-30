package com.fms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {
    private String type; // e.g., "COMPLAINT_ESCALATED", "EXPENSE_APPROVED", "MEETING_SCHEDULED"
    private String recipientEmail;
    private String recipientPhone;
    private String subject;
    private String message;
}
