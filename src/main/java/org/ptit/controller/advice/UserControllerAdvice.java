package org.ptit.controller.advice;


import lombok.extern.slf4j.Slf4j;
import org.ptit.exception.common.BusinessException;
import org.ptit.exception.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@Slf4j
@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> validateErrorHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> set = ex.getConstraintViolations();
        List<String> errorMessages = new ArrayList<>();
        for (Iterator<ConstraintViolation<?>> iterator = set.iterator(); iterator.hasNext();) {
            ConstraintViolation<?> next = iterator.next();
            errorMessages.add(next.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> applicationErrorHandler(BusinessException e, WebRequest request) {
        log.error("Exception: errorCode: {}, Message: {}", e.getErr().getErrCode(), e.getErr().getMessage());
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setStatus((e.getErr().getErrCode())/1000);
        errorResponse.setError(e.getErr().getMessage());
        errorResponse.setTimestamp(new Date());
        errorResponse.setPath(request.getDescription(false));
        return ResponseEntity.status(parseHttpStatus(e.getErr().getErrCode()))
                .body(errorResponse);
    }

    private static HttpStatus parseHttpStatus(int errCode) {
        return HttpStatus.valueOf(errCode/1000);
    }

}
