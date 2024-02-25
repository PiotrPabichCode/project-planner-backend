package com.piotrpabich.projectplanner.repository;

import com.piotrpabich.projectplanner.entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {
}