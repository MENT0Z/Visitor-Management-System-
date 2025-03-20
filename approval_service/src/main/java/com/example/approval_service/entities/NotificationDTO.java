package com.example.approval_service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    public Integer notificationId;

    public Integer send_to; // 1 mean visitor and 0 mean to the admin

    public String message;

    public Integer user_id;

    private LocalDateTime notificationTime;
}
