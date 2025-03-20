package com.example.visitor_service.controller;


import com.example.visitor_service.entities.Notification;
import com.example.visitor_service.entities.Visitor;
import com.example.visitor_service.services.NotificationService;
import com.example.visitor_service.services.VisitorService;
import com.example.visitor_service.utils.ApprovalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {
    @Autowired
    VisitorService visitorService;

    @Autowired
    NotificationService notificationService;

    @PostMapping("/register")
    public ResponseEntity<Visitor> registerVisitor(@RequestBody Visitor visitor) {
        Visitor savedVisitor = visitorService.registerVisitor(visitor);
        return ResponseEntity.ok(savedVisitor);
    }

    @GetMapping
    public ResponseEntity<List<Visitor>> getAllVisitors() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitorById(@PathVariable Integer id) {
        Visitor visitor = visitorService.getVisitorById(id);
        return ResponseEntity.ok(visitor);
    }

    @PutMapping("/{id}/checkout")
    public ResponseEntity<String> checkoutVisitor(@PathVariable Integer id) {
        visitorService.updateCheckOutTime(id);
        return ResponseEntity.ok("Visitor checked out successfully.");
    }
    @GetMapping("{id}/status")
    public String getStatus(@PathVariable Integer id){
        return visitorService.giveStatus(id);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Visitor> updateVisitorStatus(@PathVariable Integer id, @RequestParam String status) {
        ApprovalStatus approvalStatus = ApprovalStatus.valueOf(status);
        Visitor visitor = visitorService.updateStatus(id, approvalStatus);
        return ResponseEntity.ok(visitor);
    }

    @PutMapping("/{id}/qr")
    public ResponseEntity<Visitor> updateQr(@PathVariable Integer id, @RequestParam String qr) {
        Visitor visitor = visitorService.updateQr(id,qr);
        return ResponseEntity.ok(visitor);
    }

    @GetMapping("/notification/{role}")
    public List<Notification> getAllNotification(@PathVariable Integer role){
        return notificationService.getAllNotification(role);
    }

    @GetMapping("/notification/user/{user_id}")
    public List<Notification> getUserNotification(@PathVariable Integer user_id){
        return notificationService.getUserNotification(user_id);
    }

    @PostMapping("/notification")
    public ResponseEntity<Notification> saveNotification(@RequestBody Notification notification){
        Notification notification1 = notificationService.saveNotification(notification);
        return ResponseEntity.ok(notification1);
    }

    @GetMapping("/valid")
    public ResponseEntity<Boolean> checkValidUser(@RequestParam String contact_info, @RequestParam String password) {
        return ResponseEntity.ok(visitorService.checkValidUser(contact_info, password));
    }


    @GetMapping("/findByContact")
    public ResponseEntity<Visitor> getVisitorByContact(@RequestParam String contactInfo) {
        Visitor visitor = visitorService.findUserByContactInfo(contactInfo);
        if (visitor != null) {
            return ResponseEntity.ok(visitor);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
