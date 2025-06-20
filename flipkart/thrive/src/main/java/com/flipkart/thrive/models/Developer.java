package com.flipkart.thrive.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Developer extends User {
    private Project currentProject;
    // Using AtomicInteger to ensure thread-safe increments for project completions.
    private final AtomicInteger completedProjects;

    public Developer(String name) {
        super(name);
        this.completedProjects = new AtomicInteger(0);
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }

    public int getCompletedProjects() {
        return completedProjects.get();
    }

    public void incrementCompletedProjects() {
        this.completedProjects.incrementAndGet();
    }
} 