package com.seohamin.week9.domain.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

// Q2. 엔티티 어노테이션 때문에 엔티티 클래스는 데이터베이스 테이블에, 필드는 컬럼, 객체는 행 하나에 대응하게 된다.
// @Table을 통해 이름을 지정할 수도 있다.
@Entity // Q1. 이 클래스가 엔티티를 나타내는 클래스라고 JPA에게 알려주는 어노테이션
@Table(name = "note_sql")
@EntityListeners(AuditingEntityListener.class)
public class Note {
    @Id // Q1. 이 필드가 이 엔티티의 id (PK)라는 것을 알려주는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Q1. 해당 컬럼에 AUTO INCREMENT를 적용하라고 전달하는 어노테이션이다.
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 2048, nullable = false)
    private String content;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(length = 100, nullable = false)
    private String category;

    @Column(length = 100, nullable = true)
    private String hashTag;

    @CreatedDate
    @Column(nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    public Note(
            final String title,
            final String content,
            final String author,
            final String category,
            final String hashTag
    ) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.hashTag = hashTag;
    }

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getHashTag() {
        return hashTag;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void updateTitle(final String title) {
        this.title = title;
    }

    public void updateContent(final String content) {
        this.content = content;
    }

    public void updateAuthor(final String author) {
        this.author = author;
    }

    public void updateCategory(final String category) {
        this.category = category;
    }

    public void updateHashTag(final String hashTag) {
        this.hashTag = hashTag;
    }
}
