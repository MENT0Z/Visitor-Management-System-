package com.example.pre_approve_service.repository;

import com.example.pre_approve_service.entities.PreApproval;
import com.example.pre_approve_service.utils.PreApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PreApprovalRepository extends JpaRepository<PreApproval, Integer> {

    long countByEmployeeIdAndScheduledStartTime(Integer employeeId, LocalDateTime scheduledStartTime);

    Optional<PreApproval> findByVisitorIdAndStatus(Integer visitorId, PreApprovalStatus status);

    @Query("SELECT p FROM PreApproval p WHERE p.scheduledEndTime < :now AND p.status = 'APPROVED'")
    List<PreApproval> findExpiredRequests(@Param("now") LocalDateTime now);

}
