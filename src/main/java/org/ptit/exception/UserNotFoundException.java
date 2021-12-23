package org.ptit.exception;

//import liquibase.pro.packaged.B;
//import org.ptit.exception.common.BusinessException;
//import org.ptit.exception.common.ServiceError;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
