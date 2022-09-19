package br.com.challenge.modules.handler;

import br.com.challenge.modules.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    final String messageBadRequest = "Bad Request Exception, Check the Documentation";
    final String messageNOTACCEPTABLE = "Not Acceptable Exception, Check the Documentation";

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException exception){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title(messageBadRequest)
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<PSQLExceptionDetails> handlerPSQLExceptionException(PSQLException exception){
        return new ResponseEntity<>(
                PSQLExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_ACCEPTABLE.value())
                        .title(messageNOTACCEPTABLE)
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<DataIntegrityExceptionDetails> handlerdataIntegrityException(DataIntegrityException exception){
        return new ResponseEntity<>(
                DataIntegrityExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title(messageBadRequest)
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

}
