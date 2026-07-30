package com.fms.service;

import org.springframework.stereotype.Service;

@Service
public class GoogleMeetService {

    // Dummy integration to create a Google Meet link
    public String createMeeting(String summary, String description) {
        System.out.println("Creating Google Meet link for: " + summary);
        return "https://meet.google.com/dummy-fms-link";
    }
}
