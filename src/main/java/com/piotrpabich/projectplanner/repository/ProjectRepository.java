package com.piotrpabich.projectplanner.repository;

import com.piotrpabich.projectplanner.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}