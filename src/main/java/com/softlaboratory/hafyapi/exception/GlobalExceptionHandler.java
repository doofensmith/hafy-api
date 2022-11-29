package com.softlaboratory.hafyapi.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.softlaboratory.hafyapi.util.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {InvalidFormatException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleBadRequest(Exception e) {
        log.error("Error message : {}", e.getMessage());
        log.trace("Trace message : ", e);

        return ResponseUtil.build(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleAcessDenied(Exception e) {
        return ResponseUtil.build(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase(), null);
    }

}
