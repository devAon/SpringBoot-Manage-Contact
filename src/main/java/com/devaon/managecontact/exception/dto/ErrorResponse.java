package com.devaon.managecontact.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;

    public static ErrorResponse of(HttpStatus httpStatus, String message){
        return new ErrorResponse(httpStatus.value(), message);
    }

    public static ErrorResponse of(HttpStatus httpStatus, FieldError fieldError){
        if(fieldError == null){
            return new ErrorResponse(httpStatus.value(), "invalid params" );
        }else{
            return new ErrorResponse(httpStatus.value(), fieldError.getDefaultMessage());
        }
    }
}
