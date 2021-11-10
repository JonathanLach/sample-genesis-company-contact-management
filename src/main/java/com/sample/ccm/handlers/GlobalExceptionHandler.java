package com.sample.ccm.handlers;

import com.sample.ccm.exception.VatAlreadyExistException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> onResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        Map<String, String> errorResponse = createErrorResponse(request.getRequestURI(), HttpStatus.NOT_FOUND);
        errorResponse.put("message", e.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VatAlreadyExistException.class)
    public ResponseEntity<Object> onVatAlreadyExistException(VatAlreadyExistException e, HttpServletRequest request) {
        Map<String, String> errorResponse = createErrorResponse(request.getRequestURI(), HttpStatus.BAD_REQUEST);
        errorResponse.put("message", e.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> onMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errorResponse = createErrorResponse(request.getRequestURI(), HttpStatus.BAD_REQUEST);
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorResponse.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> createErrorResponse(String path, HttpStatus status) {
        final Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());
        errorResponse.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        errorResponse.put("path", path);
        return errorResponse;
    }
}