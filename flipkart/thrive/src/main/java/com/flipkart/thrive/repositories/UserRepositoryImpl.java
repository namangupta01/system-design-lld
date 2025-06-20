package com.flipkart.thrive.repositories;

import com.flipkart.thrive.models.Developer;
import com.flipkart.thrive.models.Lead;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryImpl implements IUserRepository {
    private final Map<String, Developer> developers = new ConcurrentHashMap<>();
    private final Map<String, Lead> leads = new ConcurrentHashMap<>();

    @Override
    public Developer saveDeveloper(Developer developer) {
        developers.put(developer.getId(), developer);
        return developer;
    }

    @Override
    public Lead saveLead(Lead lead) {
        leads.put(lead.getId(), lead);
        return lead;
    }

    @Override
    public Optional<Developer> findDeveloperById(String id) {
        return Optional.ofNullable(developers.get(id));
    }

    @Override
    public Optional<Lead> findLeadById(String id) {
        return Optional.ofNullable(leads.get(id));
    }

    @Override
    public Collection<Developer> findAllDevelopers() {
        return developers.values();
    }
} 