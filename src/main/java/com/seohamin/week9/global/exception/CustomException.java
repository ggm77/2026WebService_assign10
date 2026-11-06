package com.seohamin.week9.global.exception;

import com.seohamin.week9.global.exception.constant.ExceptionCode;

public class CustomException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public CustomException(ExceptionCode exceptionCode) {
        super("");
        this.exceptionCode = exceptionCode;
    }
    public CustomException(ExceptionCode exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
