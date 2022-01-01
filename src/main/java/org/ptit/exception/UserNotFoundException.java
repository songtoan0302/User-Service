package org.ptit.exception;

import org.ptit.exception.common.BusinessException;
import org.ptit.exception.common.ServiceError;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super(ServiceError.USER_NOT_FOUND_EXCEPTION);
    }
}
