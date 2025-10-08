package com.javaweb.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(500, "User already existed", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(500, "User not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(501, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    ROLE_EXISTED(500, "Role already existed", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(500, "You do not permission", HttpStatus.FORBIDDEN),
    INVALID_PASSWORD(500, "Invalid Password", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
