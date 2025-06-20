package com.flipkart.thrive.services;

import com.flipkart.thrive.enums.ProjectStatus;
import com.flipkart.thrive.models.Project;
import com.flipkart.thrive.repositories.IProjectRepository;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProjectCancellationService implements IProjectCancellationService {

    private final IProjectRepository projectRepository;
    private final INotificationService notificationService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final long PROJECT_EXPIRATION_TIME_SECONDS = 10;

    public ProjectCancellationService(IProjectRepository projectRepository, INotificationService notificationService) {
        this.projectRepository = projectRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void start() {
        this.scheduler.scheduleAtFixedRate(this::cancelUnclaimedProjects, 11, 11, TimeUnit.SECONDS);
    }

    private void cancelUnclaimedProjects() {
        LocalDateTime now = LocalDateTime.now();
        projectRepository.findAllProjects().stream()
                .filter(p -> p.getStatus() == ProjectStatus.OPEN &&
                        p.getCreationTime().plusSeconds(PROJECT_EXPIRATION_TIME_SECONDS).isBefore(now))
                .forEach(project -> {
                    project.lock();
                    try {
                        // Re-check status after acquiring the lock to prevent a race condition
                        // where the project might have been claimed just before the lock was acquired.
                        if (project.getStatus() == ProjectStatus.OPEN) {
                            project.setStatus(ProjectStatus.CANCELLED);
                            notificationService.notify(project.getLead(), "Project " + project.getName() + " was automatically cancelled due to inactivity.");
                            System.out.println("Project " + project.getName() + " was automatically cancelled.");
                        }
                    } finally {
                        project.unlock();
                    }
                });
    }

    @Override
    public void shutdown() {
        scheduler.shutdown();
    }
} 