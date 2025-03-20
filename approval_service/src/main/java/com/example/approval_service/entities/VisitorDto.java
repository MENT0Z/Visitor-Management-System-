package com.example.approval_service.entities;


import com.example.approval_service.Utils.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitorDto {
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
    private ApprovalStatus approvalStatus;
    private boolean has_qr;
    private String qr;
}

