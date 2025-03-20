package com.example.approval_service.service;

import com.example.approval_service.entities.Approval;
import com.example.approval_service.entities.NotificationDTO;
import com.example.approval_service.entities.VisitorDto;
import com.example.approval_service.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApprovalService {
    @Autowired
    ApprovalRepository approvalRepository;

    private final VisitorClient visitorClient;

    public ApprovalService(VisitorClient visitorClient) {
        this.visitorClient = visitorClient;
    }


    public List<VisitorDto> getAllVisitors() {
        return visitorClient.getAllVisitors();
    }

    public void updateVisitorStatus(Integer visitorId, String status) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage("Your Visiting Request has been "+status);
        notificationDTO.setNotificationTime(LocalDateTime.now());
        notificationDTO.setSend_to(1);
        notificationDTO.setUser_id(visitorId);
        sendNotification(notificationDTO);
        visitorClient.updateVisitorStatus(visitorId, status);
    }

    public void sendNotification(NotificationDTO notificationDTO){
        visitorClient.sendNotification(notificationDTO);
    }

    public Approval addAdmin(Approval approval){
        return approvalRepository.save(approval);
    }

    public boolean checkValidAdmin(String contactInfo, String password) {
        return approvalRepository.findByContactInfo(contactInfo.trim())
                .map(admin -> admin.getPassword().trim().equals(password.trim()))
                .orElse(false);
    }
}