package com.seohamin.week9.global.exception.response;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ExceptionResponse(
        Instant timestamp,
        HttpStatus status,
        String code,
        String message
) {
}
