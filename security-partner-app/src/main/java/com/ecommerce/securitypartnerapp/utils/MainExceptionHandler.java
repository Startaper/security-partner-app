package com.ecommerce.securitypartnerapp.utils;

import com.ecommerce.securitypartnerapp.exceptions.AuthException;
import com.ecommerce.securitypartnerapp.models.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ResponseError> handleException(AuthException e) {
        ResponseError response = new ResponseError().toBuilder()
                .errorCode(HttpStatus.FORBIDDEN.value())
                .error(e.getMessage())
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

}
