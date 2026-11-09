package com.seohamin.week9.domain.repository;

import com.seohamin.week9.domain.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Q3. JpaRepository에는 데이터베이스 테이블과 매핑할 엔티티와, 그 엔티티의 PK값의 타입을 적어야한다. 처음 적는게 엔티티, 뒤에 적는게 PK의 타입이다.
public interface NoteRepository extends JpaRepository<Note, Long> {
    /*
    Q4.
    Note save(Note e)
    - 인자로 받은 엔티티를 저장하는 메서드이다. 이미 저장된 엔티티라면 수정사항을 DB에 반영한다.
    - CRUD에서 Create와 Update를 담당한다.

    Optional<Note> findById(Long id)
    - DB 테이블에서 인자로 받은 id를 PK로 가지는 엔티티 하나를 찾아서 리턴한다.
    - 찾는 값이 없을 수 있으므로 Optional에 담아 반환한다.
    - CRUD에서 Read를 담당한다.

    List<Note> findAll()
    - DB에서 테이블에 존재하는 모든 값을 리스트 형태로 리턴한다.
    - 리턴할 데이터가 없어도 빈리스트를 반환한다.
    - CRUD에서 Read를 담당한다.

    void deleteById(Long id)
    - DB 테이블에서 인자로 받은 id를 PK로 가지는 엔티티 하나를 찾아서 삭제한다.
    - CRUD에서 Delete를 담당한다.

    void delete(Note e)
    - 인자로 받은 엔티티를 DB 테이블에서 삭제한다.
    - CRUD에서 Delete를 담당한다.
     */

    // Q5. findById()의 반환 값이 Optional인 이유는 찾는 값이 없는 경우에 대비하기 위해 Optional을 사용한다(NPE 방지).

    // Q6.
    // JpaRepository에서 기본 제공하는 메서드들은 모든 엔티티에 공통인 PK, 전체기준 CRUD이고,
    // 개발자가 직접 작성하는 Query Method는 엔티티의 특정 필드 기준으로 쿼리를 날릴 수 있다.
    // 또한 Query Method는 메서드명만 규칙에 맞게 선언하면 JPA가 알아서 SQL을 작성한다.
    List<Note> findByTitleContainingIgnoreCase(final String keyword);
    List<Note> findByAuthorAndCategory(final String author, final String category);
    List<Note> findByCategoryOrderByCreatedAtDesc(final String category);
}
