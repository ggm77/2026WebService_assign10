package com.seohamin.week9.global.exception.constant;

import org.springframework.http.HttpStatus;

public enum ExceptionCode {
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "올바르지 않은 요청입니다."),
    ID_NOT_FOUND(HttpStatus.NOT_FOUND, "아이디를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;

    ExceptionCode(
            final HttpStatus status,
            final String message
    ) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
