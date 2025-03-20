package com.example.pre_approve_service.controller;

import com.example.pre_approve_service.entities.PreApprovalRequest;
import com.example.pre_approve_service.services.PreApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pre-approvals")
public class PreApprovalController {
    @Autowired
    private PreApprovalService preApprovalService;

    // Employee Pre-Approves a Visitor
    @PostMapping("/approve")
    public ResponseEntity<?> preApproveVisitor(@RequestBody PreApprovalRequest request) {
        return ResponseEntity.ok(preApprovalService.preApproveVisitor(request));
    }

    // Check-in API (Scans QR Code)
    @PutMapping("/{visitorId}/check-in")
    public ResponseEntity<?> checkInVisitor(@PathVariable Integer visitorId) {
        return ResponseEntity.ok(preApprovalService.checkInVisitor(visitorId));
    }

    // Auto-expire requests
    @Scheduled(fixedRate = 60000) // Runs every 1 min
    public void expireOldRequests() {
        preApprovalService.expireRequests();
    }
}
