package com.seohamin.week9.global.exception.handler;

import com.seohamin.week9.global.exception.CustomException;
import com.seohamin.week9.global.exception.response.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    // 커스텀으로 만든 예외들 처리
    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ExceptionResponse> handleException(final CustomException ex) {

        final String message;
        if (ex.getMessage() == null || ex.getMessage().isEmpty()) {
            message = ex.getExceptionCode().getMessage();
        } else {
            message = ex.getMessage();
        }

        final ExceptionResponse exceptionResponse = new ExceptionResponse(
                Instant.now(),
                ex.getExceptionCode().getStatus(),
                ex.getExceptionCode().name(),
                message
        );

        return new ResponseEntity<>(exceptionResponse, ex.getExceptionCode().getStatus());
    }

    // 나머지 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(final Exception ex) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                "Internal Server Error"
        );

        log.error("CustomizedResponseEntityExceptionHandler.handleAllExceptions message:{}", ex.getMessage(), ex);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
