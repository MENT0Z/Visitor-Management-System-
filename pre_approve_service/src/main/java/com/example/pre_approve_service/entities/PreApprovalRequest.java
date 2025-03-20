package com.example.pre_approve_service.entities;

import com.example.pre_approve_service.utils.PreApprovalStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreApprovalRequest {
    private Integer id;

    private Integer visitorId;
    private Integer employeeId;

    private LocalDateTime scheduledStartTime;
    private LocalDateTime scheduledEndTime;

    private String qrCode;
    private boolean checkedIn;


    private PreApprovalStatus status; // PENDING, APPROVED, EXPIRED


    private LocalDateTime createdAt;


}

