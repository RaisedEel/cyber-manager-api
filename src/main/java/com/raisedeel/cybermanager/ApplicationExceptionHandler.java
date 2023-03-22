package com.raisedeel.cybermanager;

import com.raisedeel.cybermanager.dto.ErrorResponse;
import com.raisedeel.cybermanager.exception.ComputerNotFoundException;
import com.raisedeel.cybermanager.exception.EndTimeBeforeStartTimeException;
import com.raisedeel.cybermanager.exception.RentalNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ComputerNotFoundException.class, RentalNotFoundException.class})
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(RuntimeException ex) {
    return new ResponseEntity<>(
        new ErrorResponse(Collections.singletonList(ex.getMessage())),
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(EndTimeBeforeStartTimeException.class)
  public ResponseEntity<ErrorResponse> handleDateMismatch(EndTimeBeforeStartTimeException ex) {
    return new ResponseEntity<>(
        new ErrorResponse(Collections.singletonList(ex.getMessage())),
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<ErrorResponse> handleEmptyDataAccessException(EmptyResultDataAccessException ex) {
    return new ResponseEntity<>(
        new ErrorResponse(Collections.singletonList("Cannot delete non existing resource")),
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    return new ResponseEntity<>(
        new ErrorResponse(Collections.singletonList("Error found while updating the database: " + ex.getMessage())),
        HttpStatus.BAD_REQUEST
    );
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    List<String> errors = ex.getBindingResult().getAllErrors()
        .stream()
        .map(ObjectError::getDefaultMessage)
        .toList();

    return new ResponseEntity<>(
        new ErrorResponse(errors),
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception ex) {
    return new ResponseEntity<>(
        new ErrorResponse(Collections.singletonList("An unexpected error occurred: " + ex.getMessage())),
        HttpStatus.INTERNAL_SERVER_ERROR
    );
  }
}
