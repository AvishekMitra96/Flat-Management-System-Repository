package com.fms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "merchant_expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String merchantName;
    private String description;
    
    @Column(nullable = false)
    private BigDecimal amount;
    
    private String documentUrl; // URL to uploaded bill/invoice
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseStatus status = ExpenseStatus.RAISED;
    
    private LocalDateTime raisedAt;
    private LocalDateTime approvedAt;
    private LocalDateTime processedAt;

    private boolean isRecurring;
    private Integer recurringDayOfMonth;

    public enum ExpenseStatus {
        RAISED,      // By Secretary
        APPROVED,    // By President
        REJECTED,
        PROCESSED    // By Treasurer
    }
}
