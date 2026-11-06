package com.seohamin.week9.domain.dto;

import com.seohamin.week9.domain.model.Note;

import java.time.Instant;

public record NoteResponse(
        Long id,
        String title,
        String content,
        String author,
        String category,
        String hashTag,
        Instant createdAt
) {

    public static NoteResponse of(final Note note) {
        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getAuthor(),
                note.getCategory(),
                note.getHashTag(),
                note.getCreatedAt()
        );
    }
}
