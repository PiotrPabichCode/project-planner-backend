package com.piotrpabich.projectplanner.utils;

import com.piotrpabich.projectplanner.entity.*;
import com.piotrpabich.projectplanner.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class Runner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ProjectUserRepository projectUserRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final TaskRepository taskRepository;
    private final NoteRepository noteRepository;

    private final List<User> usersList = new ArrayList<>();
    private final List<Project> projectsList = new ArrayList<>();
    private final List<TaskCategory> taskCategoriesList = new ArrayList<>();
    private final List<Task> tasksList = new ArrayList<>();
    private final List<Note> notesList = new ArrayList<>();

    @Override
    public void run(String... args) {
        initializeData();
    }


    private void initializeData() {
        initializeUsers();
        initializeProjects();
        initializeTasks();
        initializeNotes();
    }

    private void initializeUsers() {
        User admin = User.builder()
                .username("admin")
                .firstName("Piotr")
                .lastName("Pabich")
                .createdAt(new Date())
                .updatedAt(new Date())
                .isDeleted(false)
                .role(ConstantUtil.ADMIN)
                .build();

        User user1 = User.builder()
                .username("johndoe")
                .firstName("John")
                .lastName("Doe")
                .createdAt(java.sql.Date.valueOf("2024-01-15"))
                .updatedAt(java.sql.Date.valueOf("2024-01-15"))
                .isDeleted(false)
                .role(ConstantUtil.USER)
                .build();

        User user2 = User.builder()
                .username("janesmith")
                .firstName("Jane")
                .lastName("Smith")
                .createdAt(java.sql.Date.valueOf("2024-01-20"))
                .updatedAt(java.sql.Date.valueOf("2024-01-20"))
                .isDeleted(false)
                .role(ConstantUtil.USER)
                .build();

        User user3 = User.builder()
                .username("alicejohnson")
                .firstName("Alice")
                .lastName("Johnson")
                .createdAt(java.sql.Date.valueOf("2024-01-17"))
                .updatedAt(java.sql.Date.valueOf("2024-01-17"))
                .isDeleted(false)
                .role(ConstantUtil.USER)
                .build();

        User user4 = User.builder()
                .username("bobwilliams")
                .firstName("Bob")
                .lastName("Williams")
                .createdAt(java.sql.Date.valueOf("2024-01-12"))
                .updatedAt(java.sql.Date.valueOf("2024-01-12"))
                .isDeleted(false)
                .role(ConstantUtil.USER)
                .build();

        admin.setFriends(Arrays.asList(user1, user2, user4));
        user1.setFriends(Arrays.asList(admin, user2, user3, user4));
        user2.setFriends(Arrays.asList(admin, user1));
        user3.setFriends(Arrays.asList(user1, user4));
        user4.setFriends(Arrays.asList(admin, user1, user3));

        usersList.addAll(Arrays.asList(admin, user1, user2, user3, user4));

        userRepository.saveAll(usersList);
    }
    private void initializeProjects() {
        Project project1 = Project.builder()
                .name("Web Development Project")
                .description("Building a modern web application")
                .githubUrl("https://github.com/web-project")
                .createdAt(java.sql.Date.valueOf("2024-02-05"))
                .updatedAt(java.sql.Date.valueOf("2024-02-05"))
                .position(1L)
                .isCompleted(false)
                .build();
        List<ProjectUser> project1Users = Arrays.asList(
                ProjectUser.builder().role(ConstantUtil.PROJECT_OWNER).user(usersList.get(0)).project(project1).build(),
                ProjectUser.builder().role(ConstantUtil.COLLABORATOR).user(usersList.get(1)).project(project1).build(),
                ProjectUser.builder().role(ConstantUtil.COLLABORATOR).user(usersList.get(2)).project(project1).build()
        );
        project1.setUsers(project1Users);

        Project project2 = Project.builder()
                .name("Mobile App Development")
                .description("Creating a mobile app for Android and iOS")
                .githubUrl("https://github.com/mobile-app")
                .createdAt(java.sql.Date.valueOf("2024-02-06"))
                .updatedAt(java.sql.Date.valueOf("2024-02-06"))
                .position(2L)
                .isCompleted(false)
                .build();
        List<ProjectUser> project2Users = Arrays.asList(
                ProjectUser.builder().role(ConstantUtil.PROJECT_OWNER).user(usersList.get(0)).project(project2).build(),
                ProjectUser.builder().role(ConstantUtil.COLLABORATOR).user(usersList.get(1)).project(project2).build(),
                ProjectUser.builder().role(ConstantUtil.COLLABORATOR).user(usersList.get(4)).project(project2).build()
        );

        project2.setUsers(project2Users);

        Project project3 = Project.builder()
                .name("E-commerce Platform")
                .description("Building an online shopping platform")
                .githubUrl("https://github.com/ecommerce")
                .createdAt(java.sql.Date.valueOf("2024-02-07"))
                .updatedAt(java.sql.Date.valueOf("2024-02-07"))
                .position(3L)
                .isCompleted(false)
                .build();
        List<ProjectUser> project3Users = Arrays.asList(
                ProjectUser.builder().role(ConstantUtil.PROJECT_OWNER).user(usersList.get(1)).project(project2).build(),
                ProjectUser.builder().role(ConstantUtil.COLLABORATOR).user(usersList.get(3)).project(project2).build(),
                ProjectUser.builder().role(ConstantUtil.COLLABORATOR).user(usersList.get(4)).project(project2).build()
        );

        project3.setUsers(project3Users);

        projectsList.addAll(List.of(project1, project2, project3));
        List<ProjectUser> projectsUsers = Stream.of(project1Users, project2Users, project3Users).flatMap(Collection::stream).toList();

        projectRepository.saveAll(projectsList);
        projectUserRepository.saveAll(projectsUsers);
    }
    private void initializeTasks() {
        taskCategoriesList.addAll(List.of(
                TaskCategory.builder().name("Frontend Development").color("#3498db").icon("fa-code").build(),
                TaskCategory.builder().name("Backend Development").color("#2ecc71").icon("fa-cogs").build(),
                TaskCategory.builder().name("UI/UX Design").color("#e74c3c").icon("fa-paint-brush").build()
        ));

        Project project1 = projectsList.get(0);
        List<ProjectUser> project1Users = project1.getUsers();
        tasksList.addAll(List.of(
                Task.builder()
                        .name("Implement User Authentication")
                        .description("Develop user authentication functionality for the web application.")
                        .categories(List.of(taskCategoriesList.get(1)))
                        .project(project1)
                        .createdBy(project1Users.get(0))
                        .assignedTo(List.of(project1Users.get(1)))
                        .isImportant(true)
                        .isCompleted(false)
                        .createdAt(java.sql.Date.valueOf("2024-02-06"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-06"))
                        .build(),

                Task.builder()
                        .name("Design Responsive UI")
                        .description("Create a responsive user interface design for various device sizes.")
                        .categories(List.of(taskCategoriesList.get(0)))
                        .project(projectsList.get(0))
                        .createdBy(project1Users.get(0))
                        .assignedTo(List.of(project1Users.get(2)))
                        .isImportant(false)
                        .isCompleted(false)
                        .createdAt(java.sql.Date.valueOf("2024-02-07"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-07"))
                        .build()
        ));

        Project project2 = projectsList.get(1);
        List<ProjectUser> project2Users = project2.getUsers();
        tasksList.addAll(List.of(
                Task.builder()
                        .name("Implement User Registration")
                        .description("Develop user registration functionality for the mobile app.")
                        .categories(List.of(taskCategoriesList.get(1)))
                        .project(project2)
                        .createdBy(project2Users.get(0))
                        .assignedTo(List.of(project2Users.get(1)))
                        .isImportant(true)
                        .isCompleted(false)
                        .createdAt(java.sql.Date.valueOf("2024-02-07"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-07"))
                        .build(),

                Task.builder()
                        .name("Optimize Database Queries for Mobile App")
                        .description("Improve the performance of database queries for the mobile app.")
                        .categories(List.of(taskCategoriesList.get(1)))
                        .project(project2)
                        .createdBy(project2Users.get(0))
                        .assignedTo(List.of(project2Users.get(2)))
                        .isImportant(false)
                        .isCompleted(false)
                        .createdAt(java.sql.Date.valueOf("2024-02-09"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-09"))
                        .build()
        ));

        Project project3 = projectsList.get(2);
        List<ProjectUser> project3Users = project3.getUsers();
        tasksList.addAll(List.of(
                Task.builder()
                        .name("Implement Product Catalog")
                        .description("Develop the product catalog functionality for the E-commerce Platform.")
                        .categories(List.of(taskCategoriesList.get(0)))
                        .project(project3)
                        .createdBy(project3Users.get(0))
                        .assignedTo(List.of(project3Users.get(1)))
                        .isImportant(true)
                        .isCompleted(false)
                        .createdAt(java.sql.Date.valueOf("2024-02-08"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-08"))
                        .build(),

                Task.builder()
                        .name("Integration with Payment Gateway")
                        .description("Integrate the E-commerce Platform with a payment gateway for online transactions.")
                        .categories(List.of(taskCategoriesList.get(1)))
                        .project(project3)
                        .createdBy(project3Users.get(0))
                        .assignedTo(List.of(project3Users.get(2)))
                        .isImportant(false)
                        .isCompleted(false)
                        .createdAt(java.sql.Date.valueOf("2024-02-10"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-10"))
                        .build()
        ));

        taskCategoryRepository.saveAll(taskCategoriesList);
        taskRepository.saveAll(tasksList);
    }
    private void initializeNotes() {
        Project project1 = projectsList.get(0);
        List<Note> project1Notes = List.of(
                Note.builder()
                        .name("Key Feature Planning")
                        .description("Discuss and plan the implementation of key features for the Web Development Project.")
                        .isImportant(true)
                        .createdAt(java.sql.Date.valueOf("2024-02-10"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-10"))
                        .project(project1)
                        .build(),

                Note.builder()
                        .name("Tech Stack Evaluation")
                        .description("Evaluate and choose the appropriate technology stack for the Web Development Project.")
                        .isImportant(false)
                        .createdAt(java.sql.Date.valueOf("2024-02-11"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-11"))
                        .project(project1)
                        .build()
        );

        project1.setNotes(project1Notes);

        Project project2 = projectsList.get(1);
        List<Note> project2Notes = List.of(
                Note.builder()
                        .name("User Onboarding Experience")
                        .description("Define and design a seamless user onboarding experience for the Mobile App Development Project.")
                        .isImportant(true)
                        .createdAt(java.sql.Date.valueOf("2024-02-10"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-10"))
                        .project(project2)
                        .build(),

                Note.builder()
                        .name("API Integration Strategy")
                        .description("Plan the strategy for integrating third-party APIs into the Mobile App Development Project.")
                        .isImportant(false)
                        .createdAt(java.sql.Date.valueOf("2024-02-12"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-12"))
                        .project(project2)
                        .build()
        );

        project2.setNotes(project2Notes);

        Project project3 = projectsList.get(2);
        List<Note> project3Notes = List.of(
                Note.builder()
                        .name("Product Showcase Design")
                        .description("Create a visually appealing design for showcasing products on the E-commerce Platform.")
                        .isImportant(true)
                        .createdAt(java.sql.Date.valueOf("2024-02-09"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-09"))
                        .project(project3)
                        .build(),

                Note.builder()
                        .name("Payment Gateway Integration Details")
                        .description("Document details for integrating a secure payment gateway into the E-commerce Platform.")
                        .isImportant(false)
                        .createdAt(java.sql.Date.valueOf("2024-02-12"))
                        .updatedAt(java.sql.Date.valueOf("2024-02-12"))
                        .project(project3)
                        .build()
        );

        project3.setNotes(project3Notes);

        notesList.addAll(Stream.of(project1Notes, project2Notes, project3Notes).flatMap(Collection::stream).toList());

        noteRepository.saveAll(notesList);
    }
}
