package com.example.visitor_service.services.Impl;

import com.example.visitor_service.entities.Notification;
import com.example.visitor_service.entities.Visitor;
import com.example.visitor_service.repositories.VisitorRepository;
import com.example.visitor_service.services.NotificationService;
import com.example.visitor_service.services.VisitorService;
import com.example.visitor_service.utils.ApprovalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorRepository visitorRepository;
    @Autowired
    NotificationService notificationService;

    @Override
    public Visitor registerVisitor(Visitor visitor) {
        Notification notification = new Notification();
        notification.setMessage("Visitor with user_name "+visitor.getFullName() + " is waiting for the approval");
        notification.setNotificationTime(LocalDateTime.now());
        notification.setSend_to(0);
        notificationService.saveNotification(notification);
        visitor.setCheckInTime(LocalDateTime.now());
        return visitorRepository.save(visitor);
    }

    @Override
    public Visitor getVisitorById(Integer id) {
        return visitorRepository.findById(id).orElseThrow(()->new RuntimeException("Visitor not found"));
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    @Override
    public void updateCheckOutTime(Integer id) {
        Visitor visitor = visitorRepository.findById(id).orElseThrow(() -> new RuntimeException("Visitor Not Found"));
        visitor.setCheckOutTime(LocalDateTime.now());
        visitorRepository.save(visitor);
    }

    @Override
    public String giveStatus(Integer id) {
        Visitor visitor = visitorRepository.findById(id).orElseThrow(() -> new RuntimeException("Visitor Not Found"));
        return visitor.getApprovalStatus().toString();
    }

    @Override
    public Visitor updateStatus(Integer id, ApprovalStatus approvalStatus) {
        Visitor visitor = visitorRepository.findById(id).orElseThrow(() -> new RuntimeException("Visitor Not Found"));
        visitor.setApprovalStatus(approvalStatus);
        visitorRepository.save(visitor);
        return  visitor;
    }

    @Override
    public Visitor updateQr(Integer id, String qr) {
        Visitor visitor = visitorRepository.findById(id).orElseThrow(() -> new RuntimeException("Visitor Not Found"));
        visitor.setHas_qr(true);
        visitor.setQr(qr);
        visitorRepository.save(visitor);
        return  visitor;
    }

    @Override
    public boolean checkValidUser(String contact_info, String password) {
        return visitorRepository.findByContactInfo(contact_info.trim())
                .map(visitor -> visitor.getPassword().trim().equals(password.trim()))
                .orElse(false);
    }

    @Override
    public Visitor findUserByContactInfo(String contact_info) {
        return visitorRepository.findByContactInfo(contact_info.trim()).orElse(null);
    }

}
