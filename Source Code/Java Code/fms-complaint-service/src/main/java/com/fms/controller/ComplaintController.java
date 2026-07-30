package com.fms.controller;

import com.fms.entity.Complaint;
import com.fms.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @GetMapping
    public ResponseEntity<?> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @GetMapping("/flat/{flatId}")
    public ResponseEntity<?> getComplaintsByFlat(@PathVariable Long flatId) {
        return ResponseEntity.ok(complaintService.getComplaintsByFlatId(flatId));
    }

    @PostMapping
    public ResponseEntity<?> raiseComplaint(@RequestBody Complaint complaint) {
        return ResponseEntity.ok(complaintService.raiseComplaint(complaint));
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<?> assignComplaint(@PathVariable Long id, @RequestParam String assignee) {
        return ResponseEntity.ok(complaintService.assignComplaint(id, assignee));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<?> resolveComplaint(@PathVariable Long id, @RequestBody String notes) {
        return ResponseEntity.ok(complaintService.resolveComplaint(id, notes));
    }
}
