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

        // Q7. save()를 호출하면 내부적으로 id가 null인지 확인해,
        // 새로운 데이터인지 기존에 존재하는 데이터인지 판별하고 새로운 데이터라면 새로 생성,
        // 기존에 있던 데이터라면 해당 데이터에 덮어쓴다.
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

    // Q8. @Transactional이 달려있다면 해당 메서드 내에서 DB관련 작업이 하나의 트랜잭션으로 묶이게 되고,
    // 그 결과 트랜잭션이 커밋될 때 최초 상태와 최종 상태를 비교하며 변경 되었다면 UPDATE 쿼리를 날리는게 더티 체킹이다.
    // 그래서 더티 체킹을 한다면 save()가 없어도 반영 된다.
    // @Transactional
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

        // Q7. save()를 호출하면 내부적으로 id가 null인지 확인해,
        // 새로운 데이터인지 기존에 존재하는 데이터인지 판별하고 새로운 데이터라면 새로 생성,
        // 기존에 있던 데이터라면 해당 데이터에 덮어쓴다.
        final Note savedNote = noteRepository.save(note);

        return NoteResponse.of(savedNote);
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
