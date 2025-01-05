package com.example.task_management.client.exception;

import java.util.Collections;

import com.example.task_management.client.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorResponse<String> error
                = new ErrorResponse<>(
                        "Data Validation Failed",
                exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchTaskException.class)
    @ResponseBody
    ResponseEntity<Object> handleControllerException(HttpServletRequest req, NoSuchTaskException exception) {
        ErrorResponse<String> error
                = new ErrorResponse<>(
                "Data Validation Failed",
                Collections.singletonList(exception.getMessage())
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
