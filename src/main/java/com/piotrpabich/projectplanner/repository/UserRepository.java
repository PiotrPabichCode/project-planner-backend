package com.piotrpabich.projectplanner.repository;

import com.piotrpabich.projectplanner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}