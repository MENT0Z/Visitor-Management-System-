package com.example.approval_service.controller;

import com.example.approval_service.entities.Approval;
import com.example.approval_service.entities.VisitorDto;
import com.example.approval_service.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/approvals")
public class ApprovalController {


    @Autowired
    ApprovalService approvalService;

    @GetMapping("/visitors")
    public ResponseEntity<List<VisitorDto>> getAllVisitors() {
        return ResponseEntity.ok(approvalService.getAllVisitors());
    }


    @PutMapping("/visitors/{visitorId}/status")
    public ResponseEntity<String> updateVisitorStatus(
            @PathVariable Integer visitorId,
            @RequestParam String status) {
        approvalService.updateVisitorStatus(visitorId, status);
        return ResponseEntity.ok("Visitor status updated to: " + status);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<Approval>saveAdmin(@RequestBody Approval approval){
        return ResponseEntity.ok(approvalService.addAdmin(approval));
    }

    @GetMapping("/valid")
    public ResponseEntity<Boolean> checkValidAdmin(@RequestParam String contact_info, @RequestParam String password) {
        return ResponseEntity.ok(approvalService.checkValidAdmin(contact_info, password));
    }

}