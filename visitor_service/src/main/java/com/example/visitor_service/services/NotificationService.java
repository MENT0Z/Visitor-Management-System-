package com.example.visitor_service.services;

import com.example.visitor_service.entities.Notification;

import java.util.List;

public interface NotificationService {
    Notification saveNotification(Notification notification);
    List<Notification> getAllNotification(Integer role);
    List<Notification> getUserNotification(Integer userId);
}
