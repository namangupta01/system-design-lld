package com.flipkart.thrive.services;

import com.flipkart.thrive.models.User;

public interface INotificationService {
    void notify(User user, String message);
} 