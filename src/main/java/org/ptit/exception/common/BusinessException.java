package org.ptit.exception.common;

import java.util.LinkedHashMap;

public class BusinessException extends ServiceException {

    private static final long serialVersionUID = 1l;

    protected BusinessException(ServiceError err, Throwable ex) {
        super(err, ex);
    }

    public BusinessException(ServiceError err) {
        super(err, null);
    }
}
