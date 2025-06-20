package com.flipkart.thrive;

import com.flipkart.thrive.enums.ProjectCategory;
import com.flipkart.thrive.models.Developer;
import com.flipkart.thrive.models.Lead;
import com.flipkart.thrive.models.Project;
import com.flipkart.thrive.models.ProjectRequest;
import com.flipkart.thrive.repositories.IProjectRepository;
import com.flipkart.thrive.repositories.IUserRepository;
import com.flipkart.thrive.repositories.ProjectRepositoryImpl;
import com.flipkart.thrive.repositories.UserRepositoryImpl;
import com.flipkart.thrive.services.*;

public class Driver {

    public static void main(String[] args) throws InterruptedException {
        // Setup the application's dependency injection container.
        // This is the composition root, where all services and repositories are instantiated and wired together.
        IUserRepository userRepository = new UserRepositoryImpl();
        IProjectRepository projectRepository = new ProjectRepositoryImpl();

        // Initialize services
        INotificationService notificationService = new NotificationService();
        IProjectCancellationService projectCancellationService = new ProjectCancellationService(projectRepository, notificationService);
        IUserService userService = new UserService(userRepository);
        IProjectService projectService = new ProjectService(projectRepository, userRepository, notificationService, projectCancellationService);
        IDashboardService dashboardService = new DashboardService(userRepository, projectRepository);


        // Register users
        System.out.println("Registering users...");
        Lead lead1 = userService.registerLead("Lead1");
        Lead lead2 = userService.registerLead("Lead2");
        Developer dev1 = userService.registerDeveloper("Dev1");
        Developer dev2 = userService.registerDeveloper("Dev2");
        Developer dev3 = userService.registerDeveloper("Dev3");


        // Register projects
        Project project1 = projectService.registerProject(lead1.getId(), "K8s cluster setup", "Setup a Kubernetes cluster", ProjectCategory.DEVOPS);
        Project project2 = projectService.registerProject(lead2.getId(), "App Development", "Develop a new mobile app", ProjectCategory.FRONTEND);


        // Show available projects
        System.out.println("\nAvailable Projects");
        projectService.getAvailableProjects().forEach(p -> System.out.println(p.getName() + " by " + p.getLead().getName()));

        // Dev1 requests project2
        ProjectRequest request1 = projectService.requestProject(dev1.getId(), project2.getId());

        // Dev2 requests project2 as well
        ProjectRequest request2 = projectService.requestProject(dev2.getId(), project2.getId());

        // Lead2 accepts Dev1's request
        projectService.acceptRequest(request1.getId(), project2.getId(), lead2.getId());

        // Show project and developer details
        System.out.println("\nProject and Developer Details");
        projectService.getProjectDetails(project2.getId());
        userService.getDeveloperDetails(dev1.getId());
        userService.getDeveloperDetails(dev2.getId());

        // Dev1 starts and completes the project
        projectService.startProject(dev1.getId(), project2.getId());
        projectService.completeProject(dev1.getId(), project2.getId());

        // Lead2 rates the developer for the completed project
        projectService.rateDeveloperOnProject(lead2.getId(), project2.getId(), 4.5);

        // Show developer details after completion
        System.out.println("\nDeveloper Details After Completion");
        userService.getDeveloperDetails(dev1.getId());

        // Demonstrate project cancellation
        System.out.println("\nProject Cancellation Demo");
        projectService.cancelProject(lead1.getId(), project1.getId());
        projectService.getProjectDetails(project1.getId());

        // Demonstrate automatic cancellation (wait for it)
        System.out.println("\nAutomatic Project Cancellation Demo");
        Project project3 = projectService.registerProject(lead1.getId(), "Short-lived Project", "This should auto-cancel", ProjectCategory.QA);
        System.out.println("Waiting for project to auto-cancel...");
        Thread.sleep(15000); // Wait for more than the expiration time
        projectService.getProjectDetails(project3.getId());


        // Another developer cycle for ratings
        Project project4 = projectService.registerProject(lead1.getId(), "Backend Service", "Create a new microservice", ProjectCategory.BACKEND);
        ProjectRequest request3 = projectService.requestProject(dev2.getId(), project4.getId());
        projectService.acceptRequest(request3.getId(), project4.getId(), lead1.getId());
        projectService.startProject(dev2.getId(), project4.getId());
        projectService.completeProject(dev2.getId(), project4.getId());
        projectService.rateDeveloperOnProject(lead1.getId(), project4.getId(), 5.0);

        // Show dashboard
        dashboardService.showTopDevelopersByProjects(3);
        dashboardService.showTopDevelopersByRating(3);

        // Shutdown the scheduler
        projectService.shutdown();
    }
} 