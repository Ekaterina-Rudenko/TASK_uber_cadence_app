package by.rudenko.workflowlauncher.controller;

import by.rudenko.activityworker.util.WeatherServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(WeatherServiceException.class)
    public ResponseEntity<Map<String, String>> handleServiceException(WeatherServiceException exception) {
        return handleExceptionInternal(exception.getMessage(), exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleOtherException(Exception exception) {
        return handleExceptionInternal(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, String>> handleExceptionInternal(String message, HttpStatus status) {
        Map<String, String> content = new HashMap<>();
        content.put("status", status.toString());
        content.put("message", message);
        return new ResponseEntity<>(content, status);
    }
}
