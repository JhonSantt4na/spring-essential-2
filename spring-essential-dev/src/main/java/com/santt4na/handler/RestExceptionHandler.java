package com.santt4na.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.santt4na.exceptions.BadRequestException;
import com.santt4na.exceptions.BadRequestExceptionDatails;

@ControllerAdvice // Todos os controller tem que usar as nossas flags
public class RestExceptionHandler {

      @ExceptionHandler(BadRequestException.class)
      public ResponseEntity<BadRequestExceptionDatails> handlerBadRequestException(BadRequestException bre) {
            return new ResponseEntity<>(
                        BadRequestExceptionDatails.builder()
                                    .timestamp(LocalDateTime.now())
                                    .status(HttpStatus.BAD_REQUEST.value())
                                    .title("Bad Request Exception, Check the Documentation")
                                    .details(bre.getMessage())
                                    .developerMessage(bre.getClass().getName())
                                    .build(),
                        HttpStatus.BAD_REQUEST);
      }
}
