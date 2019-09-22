package br.com.tcb.api.controller.utils;

import br.com.tcb.api.controller.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        Response<List<String>> response = new Response();
        response.getErrors().add(ex.getCause().getMessage());
        return super.handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Response<List<String>> response = new Response();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            response.getErrors().add(fieldError.getDefaultMessage());
        }
        return super.handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
    }
}
