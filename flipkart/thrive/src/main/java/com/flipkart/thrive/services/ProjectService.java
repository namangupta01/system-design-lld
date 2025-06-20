package com.flipkart.thrive.services;

import com.flipkart.thrive.enums.ProjectCategory;
import com.flipkart.thrive.enums.ProjectStatus;
import com.flipkart.thrive.enums.RequestStatus;
import com.flipkart.thrive.exceptions.IllegalOperationException;
import com.flipkart.thrive.exceptions.NotFoundException;
import com.flipkart.thrive.models.*;
import com.flipkart.thrive.repositories.IProjectRepository;
import com.flipkart.thrive.repositories.IUserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;
    private final IUserRepository userRepository;
    private final INotificationService notificationService;
    private final IProjectCancellationService projectCancellationService;

    public ProjectService(IProjectRepository projectRepository, IUserRepository userRepository,
                          INotificationService notificationService, IProjectCancellationService projectCancellationService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
        this.projectCancellationService = projectCancellationService;
        this.projectCancellationService.start();
    }

    @Override
    public Project registerProject(String leadId, String name, String description, ProjectCategory category) {
        Lead lead = userRepository.findLeadById(leadId)
                .orElseThrow(() -> new NotFoundException("Lead not found with id: " + leadId));
        Project project = new Project(name, description, category, lead);
        projectRepository.saveProject(project);
        System.out.println(name + " project with id " + project.getId() + " registered by " + lead.getName());
        return project;
    }

    @Override
    public List<Project> getAvailableProjects() {
        return projectRepository.findAllProjects().stream()
                .filter(p -> p.getStatus() == ProjectStatus.OPEN)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectRequest requestProject(String developerId, String projectId) {
        Developer developer = userRepository.findDeveloperById(developerId)
                .orElseThrow(() -> new NotFoundException("Developer not found with id: " + developerId));
        Project project = getProject(projectId);

        // Acquire lock on the project first to prevent multiple developers from being assigned.
        project.lock();
        try {
            // Synchronize on the developer to ensure a single developer cannot request multiple projects simultaneously.
            synchronized (developer) {
                if (developer.getCurrentProject() != null) {
                    throw new IllegalOperationException("Developer is already assigned to a project.");
                }
                if (project.getStatus() != ProjectStatus.OPEN) {
                    throw new IllegalOperationException("Project is not open for requests.");
                }

                ProjectRequest request = new ProjectRequest(project, developer);
                projectRepository.saveProjectRequest(request);
                project.addRequest(request);
                notificationService.notify(project.getLead(), "New request for project " + project.getName() + " from " + developer.getName());
                System.out.println("Request with id " + request.getId() + " for project " + project.getId() + " is registered for " + developer.getName());
                return request;
            }
        } finally {
            project.unlock();
        }
    }

    @Override
    public void acceptRequest(String requestId, String projectId, String leadId) {
        Project project = getProject(projectId);
        Lead lead = userRepository.findLeadById(leadId)
                .orElseThrow(() -> new NotFoundException("Lead not found with id: " + leadId));

        project.lock();
        try {
            if (!project.getLead().equals(lead)) {
                throw new IllegalOperationException("Only the lead of the project can accept requests.");
            }
            if (project.getStatus() != ProjectStatus.OPEN) {
                throw new IllegalOperationException("Project is not in a state to be assigned.");
            }

            ProjectRequest acceptedRequest = projectRepository.findProjectRequestById(requestId)
                    .orElseThrow(() -> new NotFoundException("Request not found for this project."));
            if (!acceptedRequest.getProject().equals(project)) {
                throw new NotFoundException("Request not found for this project.");
            }

            Developer developer = acceptedRequest.getDeveloper();
            synchronized (developer) {
                if (developer.getCurrentProject() != null) {
                    throw new IllegalOperationException("Developer is already assigned to another project.");
                }

                project.setAssignedDeveloper(developer);
                developer.setCurrentProject(project);
                project.setStatus(ProjectStatus.ASSIGNED);
                acceptedRequest.setStatus(RequestStatus.ACCEPTED);
            }

            // Reject all others
            for (ProjectRequest request : project.getRequests()) {
                if (!request.equals(acceptedRequest)) {
                    request.setStatus(RequestStatus.REJECTED);
                    notificationService.notify(request.getDeveloper(), "Request for project " + project.getName() + " was rejected.");
                }
            }

            notificationService.notify(developer, "Request for project " + project.getName() + " is accepted.");
            System.out.println(developer.getName() + " request is accepted to work on " + project.getName());
        } finally {
            project.unlock();
        }
    }

    @Override
    public void cancelProject(String leadId, String projectId) {
        Project project = getProject(projectId);
        Lead lead = userRepository.findLeadById(leadId)
                .orElseThrow(() -> new NotFoundException("Lead not found with id: " + leadId));

        project.lock();
        try {
            if (!project.getLead().equals(lead)) {
                throw new IllegalOperationException("Only the lead who created the project can cancel it.");
            }
            if (project.getStatus() == ProjectStatus.ASSIGNED || project.getStatus() == ProjectStatus.IN_PROGRESS) {
                throw new IllegalOperationException("Cannot cancel a project that is already assigned or in progress.");
            }
            project.setStatus(ProjectStatus.CANCELLED);
            notificationService.notify(lead, "Project " + project.getName() + " has been cancelled.");
            System.out.println("Project " + project.getName() + " is cancelled");
        } finally {
            project.unlock();
        }
    }

    @Override
    public void startProject(String developerId, String projectId) {
        Project project = getProject(projectId);
        Developer developer = userRepository.findDeveloperById(developerId)
                .orElseThrow(() -> new NotFoundException("Developer not found with id: " + developerId));
        project.lock();
        try {
            if (project.getStatus() != ProjectStatus.ASSIGNED || !project.getAssignedDeveloper().equals(developer)) {
                throw new IllegalOperationException("Project is not assigned to this developer or not in assigned state.");
            }
            project.setStatus(ProjectStatus.IN_PROGRESS);
            notificationService.notify(project.getLead(), "Developer " + developer.getName() + " has started working on " + project.getName());
            System.out.println("Developer " + developer.getName() + " started working on " + project.getName());
        } finally {
            project.unlock();
        }
    }

    @Override
    public void completeProject(String developerId, String projectId) {
        Project project = getProject(projectId);
        Developer developer = userRepository.findDeveloperById(developerId)
                .orElseThrow(() -> new NotFoundException("Developer not found with id: " + developerId));
        project.lock();
        try {
            synchronized (developer) {
                if (project.getStatus() != ProjectStatus.IN_PROGRESS || !project.getAssignedDeveloper().equals(developer)) {
                    throw new IllegalOperationException("Project is not in progress by this developer.");
                }
                project.setStatus(ProjectStatus.COMPLETED);
                developer.setCurrentProject(null);
                developer.incrementCompletedProjects();
            }
            notificationService.notify(project.getLead(), "Project " + project.getName() + " has been completed by " + developer.getName());
            System.out.println("Project " + project.getName() + " is marked completed");
        } finally {
            project.unlock();
        }
    }

    @Override
    public void rateDeveloperOnProject(String leadId, String projectId, double rating) {
        Project project = getProject(projectId);
        Lead lead = userRepository.findLeadById(leadId)
                .orElseThrow(() -> new NotFoundException("Lead not found with id: " + leadId));

        project.lock();
        try {
            if (!project.getLead().equals(lead)) {
                throw new IllegalOperationException("Only the lead of the project can rate it.");
            }
            if (project.getStatus() != ProjectStatus.COMPLETED) {
                throw new IllegalOperationException("Only completed projects can be rated.");
            }
            if (project.getDeveloperRating() != null) {
                throw new IllegalOperationException("This project has already been rated.");
            }
            project.setDeveloperRating(rating);
            System.out.println("Project " + project.getName() + " rated " + rating + " by " + lead.getName());
        } finally {
            project.unlock();
        }
    }

    @Override
    public void getProjectDetails(String projectId) {
        Project project = getProject(projectId);
        System.out.println(project.getName() + ", " + project.getDescription() + ", " + project.getCategory() + ", " + project.getLead().getName() + ", " + project.getStatus() + ", " + (project.getAssignedDeveloper() != null ? project.getAssignedDeveloper().getName() : "UnAssigned"));
    }

    @Override
    public Project getProject(String projectId) {
        return projectRepository.findProjectById(projectId)
                .orElseThrow(() -> new NotFoundException("Project not found with id: " + projectId));
    }

    @Override
    public Collection<Project> getAllProjects() {
        return projectRepository.findAllProjects();
    }

    @Override
    public void shutdown() {
        projectCancellationService.shutdown();
    }
} 