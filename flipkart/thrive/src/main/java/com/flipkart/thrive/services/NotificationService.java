package com.flipkart.thrive.services;

import com.flipkart.thrive.models.User;

public class NotificationService implements INotificationService {

    @Override
    public void notify(User user, String message) {
        System.out.println("Notification for " + user.getName() + ": " + message);
    }
} 