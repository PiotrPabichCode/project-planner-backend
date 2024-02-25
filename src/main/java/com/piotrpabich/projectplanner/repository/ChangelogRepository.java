package com.piotrpabich.projectplanner.repository;

import com.piotrpabich.projectplanner.entity.Changelog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangelogRepository extends JpaRepository<Changelog, Long> {
}