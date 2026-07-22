package com.fms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance_dues")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long flatId;

    @Column(nullable = false)
    private BigDecimal amount;
    
    private String breakdownDescription; // E.g., "Water: 500, Sinking Fund: 1000, Base: 1500"

    @Column(nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    public enum Status {
        PENDING, PAID, OVERDUE
    }
}
