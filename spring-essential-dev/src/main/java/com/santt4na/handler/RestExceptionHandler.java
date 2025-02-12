package com.santt4na.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.santt4na.exceptions.BadRequestException;
import com.santt4na.exceptions.BadRequestExceptionDatails;
import com.santt4na.exceptions.ValidationExceptionDetails;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
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

      @ExceptionHandler(MethodArgumentNotValidException.class)
      public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
                  MethodArgumentNotValidException exception) {
            // Pegando todos os campos que estão com erros :
            List<FieldError> fieldsErrors = exception.getBindingResult().getFieldErrors();
            String fields = fieldsErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
            String fieldsMessage = fieldsErrors.stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.joining(", "));
            return new ResponseEntity<>(
                        ValidationExceptionDetails.builder()
                                    .timestamp(LocalDateTime.now())
                                    .status(HttpStatus.BAD_REQUEST.value())
                                    .title("Bad Request Exception, Invalid Fields")
                                    .details("Check the Field(s) Error")
                                    .developerMessage(exception.getClass().getName())
                                    .fields(fields)
                                    .fieldsMessage(fieldsMessage)
                                    .build(),
                        HttpStatus.BAD_REQUEST);
      }
}
