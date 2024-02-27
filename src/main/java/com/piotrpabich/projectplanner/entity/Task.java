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

    @ManyToMany
    private List<TaskCategory> categories;

    @ManyToOne
    private Project project;

    @ManyToOne
    @JsonProperty("created_by")
    private ProjectUser createdBy;

    @OneToMany
    @JsonProperty("assigned_to")
    private List<ProjectUser> assignedTo;

    @JsonProperty("is_important")
    private Boolean isImportant;

    @JsonProperty("is_completed")
    private Boolean isCompleted;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "task")
    private List<Note> notes;
}
