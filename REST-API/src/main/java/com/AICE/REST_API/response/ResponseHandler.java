package com.AICE.REST_API.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object responseObject) {
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", LocalDateTime.now());
        response.put("message", message);
        response.put("httpStatus", httpStatus);
        response.put("data", responseObject);

        return new ResponseEntity<>(response, httpStatus);
    }
}
