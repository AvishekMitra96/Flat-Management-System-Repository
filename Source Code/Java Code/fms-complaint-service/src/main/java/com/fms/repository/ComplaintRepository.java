package com.fms.repository;

import com.fms.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByFlatId(Long flatId);
    List<Complaint> findByStatus(Complaint.Status status);
    List<Complaint> findByStatusAndCreatedAtBefore(Complaint.Status status, LocalDateTime beforeTime);
}
