package com.piotrpabich.projectplanner.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String description;

    @OneToMany
    private List<TaskCategory> categories;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User createdBy;

    @OneToMany
    private List<User> assignedTo;

    @JsonProperty("is_important")
    private Boolean isImportant;

    @JsonProperty("is_completed")
    private Boolean isCompleted;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @OneToMany
    private List<Note> notes;
}
