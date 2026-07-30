package com.fms.service;

import com.fms.entity.Complaint;
import com.fms.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public List<Complaint> getComplaintsByFlatId(Long flatId) {
        return complaintRepository.findByFlatId(flatId);
    }

    public Complaint raiseComplaint(Complaint complaint) {
        complaint.setCreatedAt(LocalDateTime.now());
        complaint.setStatus(Complaint.Status.OPEN);
        complaint.setEscalated(false);
        // Here we would also send a Kafka event to Communication Service
        return complaintRepository.save(complaint);
    }

    @Transactional
    public Complaint assignComplaint(Long id, String assignee) {
        Complaint complaint = complaintRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Complaint not found"));
        
        complaint.setAssignee(assignee);
        if (complaint.getStatus() == Complaint.Status.OPEN) {
            complaint.setStatus(Complaint.Status.IN_PROGRESS);
        }
        return complaintRepository.save(complaint);
    }

    @Transactional
    public Complaint resolveComplaint(Long id, String notes) {
        Complaint complaint = complaintRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Complaint not found"));
        
        complaint.setStatus(Complaint.Status.RESOLVED);
        complaint.setResolvedAt(LocalDateTime.now());
        complaint.setResolutionNotes(notes);
        // Here we would also send a Kafka event to Communication Service
        return complaintRepository.save(complaint);
    }

    @Transactional
    public void autoEscalateComplaints() {
        // Find complaints OPEN or IN_PROGRESS older than 48 hours
        LocalDateTime threshold = LocalDateTime.now().minusHours(48);
        List<Complaint> overdueComplaints = complaintRepository.findByStatusAndCreatedAtBefore(Complaint.Status.OPEN, threshold);
        
        overdueComplaints.forEach(c -> {
            c.setStatus(Complaint.Status.ESCALATED);
            c.setEscalated(true);
            // Fire Kafka event to alert Committee members
        });
        
        complaintRepository.saveAll(overdueComplaints);
    }
}
