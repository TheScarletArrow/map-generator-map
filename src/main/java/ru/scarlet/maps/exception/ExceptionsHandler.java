package ru.scarlet.maps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler({CredentialCustomException.class, UserNotFoundException.class})
    ResponseEntity<ErrorResponse> parse(Exception e){
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(badRequest)
                .body(
                        new ErrorResponse(
                                LocalDateTime.now(),
                                badRequest.value(),
                                badRequest.getReasonPhrase(),
                                e.getMessage()
                        )
                );
    }
}
