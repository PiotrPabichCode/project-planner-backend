package com.piotrpabich.projectplanner.repository;

import com.piotrpabich.projectplanner.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}