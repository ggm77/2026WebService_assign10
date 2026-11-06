package com.seohamin.week9.domain.repository;

import com.seohamin.week9.domain.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
