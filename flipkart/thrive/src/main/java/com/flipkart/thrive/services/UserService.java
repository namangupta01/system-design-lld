package com.flipkart.thrive.services;

import com.flipkart.thrive.exceptions.NotFoundException;
import com.flipkart.thrive.models.Developer;
import com.flipkart.thrive.models.Lead;
import com.flipkart.thrive.repositories.IUserRepository;

import java.util.Collection;

public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Developer registerDeveloper(String name) {
        Developer developer = new Developer(name);
        userRepository.saveDeveloper(developer);
        System.out.println(name + " developer registered");
        return developer;
    }

    @Override
    public Lead registerLead(String name) {
        Lead lead = new Lead(name);
        userRepository.saveLead(lead);
        System.out.println(name + " lead registered");
        return lead;
    }

    @Override
    public Developer getDeveloper(String developerId) {
        return userRepository.findDeveloperById(developerId)
                .orElseThrow(() -> new NotFoundException("Developer not found with id: " + developerId));
    }

    @Override
    public Lead getLead(String leadId) {
        return userRepository.findLeadById(leadId)
                .orElseThrow(() -> new NotFoundException("Lead not found with id: " + leadId));
    }

    @Override
    public Collection<Developer> getAllDevelopers() {
        return userRepository.findAllDevelopers();
    }

    @Override
    public void getDeveloperDetails(String developerId) {
        Developer developer = getDeveloper(developerId);
        if (developer.getCurrentProject() != null) {
            System.out.println(developer.getName() + " is working on " + developer.getCurrentProject().getName());
        } else {
            System.out.println(developer.getName() + " has no project assigned");
        }
    }
} 