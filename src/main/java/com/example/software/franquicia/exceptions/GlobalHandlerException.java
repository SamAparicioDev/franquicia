package com.example.software.franquicia.exceptions;

import com.example.software.franquicia.exceptions.service.AlreadyExistsException;
import com.example.software.franquicia.exceptions.service.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Mono<String>> handlerAlreadyExistsException(AlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Mono.just(ex.getMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Mono<String>> handlerNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just(ex.getMessage()));
    }
}
