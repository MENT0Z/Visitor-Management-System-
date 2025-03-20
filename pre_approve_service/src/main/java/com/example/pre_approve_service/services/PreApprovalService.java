package com.example.pre_approve_service.services;

import com.example.pre_approve_service.entities.NotificationDTO;
import com.example.pre_approve_service.entities.PreApproval;
import com.example.pre_approve_service.entities.PreApprovalRequest;
import com.example.pre_approve_service.repository.PreApprovalRepository;
import com.example.pre_approve_service.utils.ApprovalStatus;
import com.example.pre_approve_service.utils.PreApprovalStatus;
import com.example.pre_approve_service.utils.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PreApprovalService {

    @Autowired
    private PreApprovalRepository preApprovalRepository;

    @Autowired
    private VisitorFeignClient visitorFeignClient;

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @Value("${max.preapprovals.per.employee:5}")
    private int maxPreApprovalsPerEmployee;


    public PreApproval preApproveVisitor(PreApprovalRequest request) {
        // Ensure employee doesn't exceed max pre-approvals
        long count = preApprovalRepository.countByEmployeeIdAndScheduledStartTime(request.getEmployeeId(), request.getScheduledStartTime());
        if (count >= maxPreApprovalsPerEmployee) {
            throw new RuntimeException("Max pre-approval limit reached");
        }

        // Generate QR code
        //String qrCode = qrCodeGenerator.generateQRCode(request.getVisitorId());
        String qrCode = qrCodeGenerator.generateQRCodeBase64(request.getVisitorId().toString(),200,200);
        // Save pre-approval
        PreApproval preApproval = new PreApproval();
        preApproval.setVisitorId(request.getVisitorId());
        preApproval.setEmployeeId(request.getEmployeeId());
        preApproval.setScheduledStartTime(request.getScheduledStartTime());
        preApproval.setScheduledEndTime(request.getScheduledEndTime());
        preApproval.setQrCode(qrCode);
        preApproval.setStatus(PreApprovalStatus.APPROVED);
        visitorFeignClient.updateVisitorStatus(request.getVisitorId(), ApprovalStatus.APPROVED.toString());
        visitorFeignClient.updateVisitorQr(request.getVisitorId(), qrCode);
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setNotificationTime(LocalDateTime.now());
        notificationDTO.setMessage("You are Pre-Approved for visiting at timestamp " +request.getScheduledStartTime() +" till "+ request.getScheduledEndTime());
        notificationDTO.setSend_to(1);
        notificationDTO.setUser_id(request.getVisitorId());
        visitorFeignClient.sendNotification(notificationDTO);
        return preApprovalRepository.save(preApproval);
    }


    public String checkInVisitor(Integer visitorId) {
        Optional<PreApproval> preApprovalOpt = preApprovalRepository.findByVisitorIdAndStatus(visitorId, PreApprovalStatus.APPROVED);
        if (preApprovalOpt.isEmpty()) {
            throw new RuntimeException("No valid pre-approval found for visitor");
        }

        PreApproval preApproval = preApprovalOpt.get();
        preApproval.setCheckedIn(true);
        preApproval.setStatus(PreApprovalStatus.CHECKED_IN);
        preApprovalRepository.save(preApproval);

        return "Check-in successful!";
    }


    public void expireRequests() {
        LocalDateTime now = LocalDateTime.now();
        List<PreApproval> expiredRequests = preApprovalRepository.findExpiredRequests(now);
        for (PreApproval preApproval : expiredRequests) {
            preApproval.setStatus(PreApprovalStatus.EXPIRED);
            preApprovalRepository.save(preApproval);
        }
    }
}

