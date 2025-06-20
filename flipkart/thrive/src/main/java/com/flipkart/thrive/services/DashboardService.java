package com.flipkart.thrive.services;

import com.flipkart.thrive.enums.ProjectStatus;
import com.flipkart.thrive.models.Developer;
import com.flipkart.thrive.models.Project;
import com.flipkart.thrive.repositories.IProjectRepository;
import com.flipkart.thrive.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DashboardService implements IDashboardService {

    private final IUserRepository userRepository;
    private final IProjectRepository projectRepository;

    public DashboardService(IUserRepository userRepository, IProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void showTopDevelopersByProjects(int limit) {
        System.out.println("\nTop " + limit + " Developers by Completed Projects");
        List<Developer> developers = getAllDevelopers();
        developers.stream()
                .sorted(Comparator.comparingInt(Developer::getCompletedProjects).reversed())
                .limit(limit)
                .forEach(d -> System.out.println(d.getName() + " - " + d.getCompletedProjects() + " projects"));
    }

    @Override
    public void showTopDevelopersByRating(int limit) {
        System.out.println("\nTop " + limit + " Developers by Average Rating");
        List<Developer> developers = getAllDevelopers();
        developers.stream()
                .sorted(Comparator.comparingDouble(this::getAverageRatingForDeveloper).reversed())
                .limit(limit)
                .forEach(d -> System.out.println(d.getName() + " - " + String.format("%.2f", getAverageRatingForDeveloper(d)) + " avg rating"));
    }

    private double getAverageRatingForDeveloper(Developer developer) {
        return projectRepository.findAllProjects().stream()
                .filter(p -> p.getStatus() == ProjectStatus.COMPLETED)
                .filter(p -> p.getAssignedDeveloper() != null && p.getAssignedDeveloper().equals(developer))
                .filter(p -> p.getDeveloperRating() != null)
                .mapToDouble(Project::getDeveloperRating)
                .average()
                .orElse(0.0);
    }

    private List<Developer> getAllDevelopers() {
        return new ArrayList<>(userRepository.findAllDevelopers());
    }
} 