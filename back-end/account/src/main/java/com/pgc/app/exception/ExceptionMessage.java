package com.pgc.app.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ExceptionMessage (
        String message,
        HttpStatus httpStatus,
        ZonedDateTime date
){
}
