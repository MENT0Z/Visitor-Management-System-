package com.example.approval_service.service;

import com.example.approval_service.entities.NotificationDTO;
import com.example.approval_service.entities.VisitorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//@FeignClient(url = "http://localhost:8081",value = "visitor-client")
@FeignClient(name = "VISITORSERVICE")
public interface VisitorClient {
    @GetMapping("/visitors")
    List<VisitorDto> getAllVisitors();

    @PutMapping("/visitors/{visitorId}/status")
    void updateVisitorStatus(@PathVariable Integer visitorId, @RequestParam String status);

    @PostMapping("/visitors/notification")
    void sendNotification(@RequestBody NotificationDTO notificationDTO);
}
