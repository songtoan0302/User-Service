package org.ptit.exception;

import lombok.Data;
import lombok.Getter;

@Data
public class ServiceError {

    private String message;

    public ServiceError(String message){
        this.message=message;
    }


}
