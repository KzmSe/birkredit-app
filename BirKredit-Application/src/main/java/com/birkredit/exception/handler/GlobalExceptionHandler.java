package com.birkredit.exception.handler;

import com.birkredit.exception.DataNotFoundException;
import com.birkredit.exception.response.ErrorResponse;
import com.birkredit.exception.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage(), ex);

        ErrorResponse error = new ErrorResponse();
        error.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
        error.setMessage(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getField() + " " + ex.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorResponse> handleNoResultException(NoResultException ex) {
        logger.error(ex.getMessage(), ex);

        ErrorResponse error = new ErrorResponse();
        error.setTitle(HttpStatus.NOT_FOUND.getReasonPhrase());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException ex) {
        logger.error(ex.getMessage(), ex);

        ErrorResponse error = new ErrorResponse();
        error.setTitle(HttpStatus.NOT_FOUND.getReasonPhrase());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error(ex.getMessage(), ex);

        ErrorResponse error = new ErrorResponse();
        error.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknown(Exception ex) {
        logger.error(ex.getMessage(), ex);

        ErrorResponse error = new ErrorResponse();
        error.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setMessage(ResponseMessage.ERROR_INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
