package com.flipkart.thrive.repositories;

import com.flipkart.thrive.models.Developer;
import com.flipkart.thrive.models.Lead;
import com.flipkart.thrive.models.User;

import java.util.Collection;
import java.util.Optional;

public interface IUserRepository {
    Developer saveDeveloper(Developer developer);
    Lead saveLead(Lead lead);
    Optional<Developer> findDeveloperById(String id);
    Optional<Lead> findLeadById(String id);
    Collection<Developer> findAllDevelopers();
} 