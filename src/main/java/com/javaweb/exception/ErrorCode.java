package com.javaweb.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Error"),
    USER_EXISTED(500, "User already existed"),
    USER_NOT_FOUND(500, "User not found"),
    UNAUTHENTICATED(501, "Unauthenticated"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

}
