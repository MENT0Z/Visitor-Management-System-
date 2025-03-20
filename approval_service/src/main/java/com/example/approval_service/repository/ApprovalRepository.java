package com.example.approval_service.repository;

import com.example.approval_service.entities.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval,Integer> {
    Optional<Approval> findByContactInfo(String contactInfo);
}
