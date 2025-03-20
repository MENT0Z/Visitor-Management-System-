package com.example.pre_approve_service.entities;




import com.example.pre_approve_service.utils.ApprovalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private boolean has_qr;
    private String qr;
}

