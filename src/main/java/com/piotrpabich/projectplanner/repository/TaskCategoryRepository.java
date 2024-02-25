package com.piotrpabich.projectplanner.repository;

import com.piotrpabich.projectplanner.entity.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
}