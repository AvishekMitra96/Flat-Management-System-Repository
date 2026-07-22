package com.fms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "flats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String flatNumber;

    @Column(nullable = true)
    private Long ownerUserId; // Relates to User in auth service

    private String ownerName;
    private String ownerEmail;
    private String ownerPhone;

    @ManyToOne
    @JoinColumn(name = "society_id", nullable = false)
    private Society society;
}
