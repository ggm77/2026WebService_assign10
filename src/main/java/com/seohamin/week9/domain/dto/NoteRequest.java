package com.seohamin.week9.domain.dto;

public record NoteRequest(
        String title,
        String content,
        String author,
        String category,
        String hashTag
) {
}
