package com.flipkart.thrive.services;

import com.flipkart.thrive.models.Developer;
import com.flipkart.thrive.models.Lead;

import java.util.Collection;

public interface IUserService {
    Developer registerDeveloper(String name);
    Lead registerLead(String name);
    Developer getDeveloper(String developerId);
    Lead getLead(String leadId);
    Collection<Developer> getAllDevelopers();
    void getDeveloperDetails(String developerId);
} 