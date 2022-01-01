package org.ptit.exception.common;

import lombok.Getter;

@Getter
public enum ServiceError {
    USER_NOT_FOUND_EXCEPTION(404001, "Not Found");
    private int errCode;
    private String message;

    ServiceError(int errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }
}
