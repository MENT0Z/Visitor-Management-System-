package com.example.pre_approve_service.services;

import com.example.pre_approve_service.entities.NotificationDTO;
import com.example.pre_approve_service.entities.Visitor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient(name = "visitor-service", url = "http://localhost:8081")
@FeignClient(name = "VISITORSERVICE")
public interface VisitorFeignClient {
    @PutMapping("/visitors/{visitorId}/status")
    void updateVisitorStatus(@PathVariable Integer visitorId, @RequestParam String status);

    @PutMapping("/visitors/{visitorId}/qr")
    void updateVisitorQr(@PathVariable Integer visitorId, @RequestParam String qr);

    @GetMapping("/visitors/{visitorId}")
    Visitor getVisitorById(@PathVariable Integer visitorId);

    @PostMapping("/visitors/notification")
    void sendNotification(@RequestBody NotificationDTO notificationDTO);
}
