package com.softlaboratory.hafyapi.util;

import com.softlaboratory.hafyapi.domain.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ResponseUtil {

    private ResponseUtil() {
        //
    }

    public static ResponseEntity<Object> build(HttpStatus httpStatus, String message, Object data) {
        return new ResponseEntity<>(ApiResponse.builder()
                .timestamp(LocalDateTime.now(ZoneId.of("GMT+7")))
                .responseCode(httpStatus.value())
                .message(message)
                .data(data)
                .build(), httpStatus);
    }

}
