package com.piotrpabich.projectplanner.repository;

import com.piotrpabich.projectplanner.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}