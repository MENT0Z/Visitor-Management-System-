package com.example.visitor_service.entities;


import com.example.visitor_service.utils.ApprovalStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Data
@NoArgsConstructor
@Table(name="visitors")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;
    private String contactInfo;
    private String purposeOfVisit;
    private String hostEmployee;
    private String companyName;
    private String password;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private String photoUrl; // URL to visitor's photo

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING; // Default PENDING

    private boolean has_qr=false;
    @Lob  // Large Object - for large data like QR codes
    @Column(columnDefinition = "TEXT") // Ensures a large storage capacity
    private String qr = "";
}
