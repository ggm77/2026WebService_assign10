package com.seohamin.week9.domain.service;

import com.seohamin.week9.domain.dto.NoteRequest;
import com.seohamin.week9.domain.dto.NoteResponse;
import com.seohamin.week9.domain.model.Note;
import com.seohamin.week9.domain.repository.NoteRepository;
import com.seohamin.week9.global.exception.CustomException;
import com.seohamin.week9.global.exception.constant.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(final NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteResponse noteCreate(
            final NoteRequest noteRequest
    ) {
        if (
                noteRequest == null
                || noteRequest.title() == null || noteRequest.title().isBlank()
                || noteRequest.content() == null || noteRequest.content().isBlank()
                || noteRequest.author() == null || noteRequest.author().isBlank()
                || noteRequest.category() == null || noteRequest.category().isBlank()
                || noteRequest.hashTag() == null || noteRequest.hashTag().isBlank()
        ) {
            throw new CustomException(ExceptionCode.INVALID_REQUEST);
        }

        final Note note = new Note(
                noteRequest.title(),
                noteRequest.content(),
                noteRequest.author(),
                noteRequest.category(),
                noteRequest.hashTag()
        );

        final Note savedNote = noteRepository.save(note);

        return NoteResponse.of(savedNote);
    }

    public NoteResponse getNote(final Long id) {
        if (id == null) {
            throw new CustomException(ExceptionCode.ID_NOT_FOUND);
        }

        return NoteResponse.of(
                noteRepository.findById(id)
                        .orElseThrow(() -> new CustomException(ExceptionCode.ID_NOT_FOUND))
        );
    }

    public List<NoteResponse> getAllNote() {
        return new ArrayList<>(
                noteRepository.findAll().stream()
                        .map(NoteResponse::of)
                        .toList()
        );
    }

    @Transactional
    public NoteResponse updateNote(
            final NoteRequest noteRequest,
            final Long id
    ) {
        if (id == null) {
            throw new CustomException(ExceptionCode.ID_NOT_FOUND);
        }
        if (
                noteRequest == null
                || noteRequest.title() == null || noteRequest.title().isBlank()
                || noteRequest.content() == null || noteRequest.content().isBlank()
                || noteRequest.author() == null || noteRequest.author().isBlank()
                || noteRequest.category() == null || noteRequest.category().isBlank()
                || noteRequest.hashTag() == null || noteRequest.hashTag().isBlank()
        ) {
            throw new CustomException(ExceptionCode.INVALID_REQUEST);
        }

        final Note note = noteRepository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionCode.ID_NOT_FOUND));

        note.updateTitle(noteRequest.title());
        note.updateContent(noteRequest.content());
        note.updateAuthor(noteRequest.author());
        note.updateCategory(noteRequest.category());
        note.updateHashTag(noteRequest.hashTag());

        return NoteResponse.of(note);
    }

    public void deleteNote(final Long id) {
        if (id == null) {
            throw new CustomException(ExceptionCode.ID_NOT_FOUND);
        }

        if (!noteRepository.existsById(id)) {
            throw new CustomException(ExceptionCode.ID_NOT_FOUND);
        }

        noteRepository.deleteById(id);
    }
}
