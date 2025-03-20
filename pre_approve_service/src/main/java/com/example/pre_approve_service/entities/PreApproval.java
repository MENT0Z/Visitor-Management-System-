package com.example.pre_approve_service.entities;

import com.example.pre_approve_service.utils.PreApprovalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer visitorId;
    private Integer employeeId;

    private LocalDateTime scheduledStartTime;
    private LocalDateTime scheduledEndTime;

    private String qrCode;
    private boolean checkedIn;

    @Enumerated(EnumType.STRING)
    private PreApprovalStatus status; // PENDING, APPROVED, EXPIRED

    @CreationTimestamp
    private LocalDateTime createdAt;
}
