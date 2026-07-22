package com.fms.repository;

import com.fms.entity.MaintenanceDue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaintenanceDueRepository extends JpaRepository<MaintenanceDue, Long> {
    List<MaintenanceDue> findByFlatId(Long flatId);
    List<MaintenanceDue> findByStatus(MaintenanceDue.Status status);
}
