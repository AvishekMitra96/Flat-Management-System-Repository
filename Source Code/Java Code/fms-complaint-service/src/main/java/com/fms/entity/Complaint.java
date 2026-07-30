package com.fms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long flatId;

    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.OPEN;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    private LocalDateTime resolvedAt;
    
    private String assignee; // Admin or worker assigned to resolve this
    
    private String resolutionNotes;
    
    private boolean isEscalated = false;

    public enum Category {
        PLUMBING, ELECTRICAL, SECURITY, CARPENTRY, SANITATION, OTHER
    }
    
    public enum Priority {
        LOW, MEDIUM, HIGH, CRITICAL
    }
    
    public enum Status {
        OPEN, IN_PROGRESS, RESOLVED, ESCALATED
    }
}
