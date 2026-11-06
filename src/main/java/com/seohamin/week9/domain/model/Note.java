package com.seohamin.week9.domain.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "note_sql")
@EntityListeners(AuditingEntityListener.class)
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
