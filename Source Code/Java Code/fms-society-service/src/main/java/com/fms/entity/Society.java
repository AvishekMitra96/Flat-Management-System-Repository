package com.fms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "societies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Society {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String registrationNo;

    @Column(unique = true, nullable = false)
    private String pan;

    private String address;
}
