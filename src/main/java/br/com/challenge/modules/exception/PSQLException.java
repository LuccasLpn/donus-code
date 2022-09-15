package br.com.challenge.modules.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PSQLException extends RuntimeException {
    public PSQLException(String message) {
        super(message);
    }
}
