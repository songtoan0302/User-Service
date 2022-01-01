package org.ptit.exception.common;

import lombok.Getter;

import java.util.*;

@Getter
public abstract class ServiceException extends RuntimeException {
    private ServiceError err;

    private static final long serialVersionUID = 1L;

    protected ServiceException(ServiceError err, Throwable ex) {
        super(err.getMessage(), ex);
        this.err=err;
    }

}
