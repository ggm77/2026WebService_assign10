package com.seohamin.week9.domain.controller;

import com.seohamin.week9.domain.dto.NoteRequest;
import com.seohamin.week9.domain.dto.NoteResponse;
import com.seohamin.week9.domain.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(final NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public ResponseEntity<NoteResponse> createNote(
            @RequestBody final NoteRequest noteRequest
    ) {
        return ResponseEntity.ok().body(noteService.noteCreate(noteRequest));
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteResponse> getNote(
            @PathVariable(value = "id") final String idStr
    ) {
        return ResponseEntity.ok().body(noteService.getNote(Long.parseLong(idStr)));
    }

    @GetMapping("/notes")
    public ResponseEntity<List<NoteResponse>> getAllNotes() {
        return ResponseEntity.ok().body(noteService.getAllNote());
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable(value = "id") final String idStr,
            @RequestBody final NoteRequest noteRequest
    ) {
        return ResponseEntity.ok().body(noteService.updateNote(
                noteRequest,
                Long.parseLong(idStr)
        ));
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<NoteResponse> deleteNote(
            @PathVariable(value = "id") final String idStr
    ) {
        noteService.deleteNote(Long.parseLong(idStr));

        return ResponseEntity.noContent().build();
    }
}
