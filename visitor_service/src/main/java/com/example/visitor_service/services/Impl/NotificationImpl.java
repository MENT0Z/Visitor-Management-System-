package com.example.visitor_service.services.Impl;

import com.example.visitor_service.entities.Notification;
import com.example.visitor_service.repositories.NotificationRepository;
import com.example.visitor_service.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotification(Integer role) {
        List<Notification>allNotifications = notificationRepository.findAll();
        List<Notification>onlyWithRole = new ArrayList<>();
        for(Notification notification : allNotifications){
            if(notification.getSend_to() == role){
                onlyWithRole.add(notification);
            }
        }
        return onlyWithRole;
    }

    @Override
    public List<Notification> getUserNotification(Integer userId) {
        List<Notification>allNotifications = notificationRepository.findAll();
        List<Notification>onlyWithRole = new ArrayList<>();
        for(Notification notification : allNotifications){
            if(notification.getSend_to() == 1 && notification.getUser_id() == userId){
                onlyWithRole.add(notification);
            }
        }
        return onlyWithRole;
    }
}
