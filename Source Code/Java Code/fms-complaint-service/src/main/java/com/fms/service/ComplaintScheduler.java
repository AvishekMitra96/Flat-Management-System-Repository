package com.fms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComplaintScheduler {
    
    private final ComplaintService complaintService;

    // Run every hour to check for 48h SLA breaches
    @Scheduled(fixedRate = 3600000)
    public void scheduleAutoEscalation() {
        complaintService.autoEscalateComplaints();
    }
}
