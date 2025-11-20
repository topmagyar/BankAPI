package com.dev.bank.handlers;

import com.dev.bank.models.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream().map(er -> er.getField() + ": " + er.getDefaultMessage()).reduce((e1, e2) -> e1 + ";   " + e2).orElse("Validation Error");
        System.out.println("Validation Error: " + message);

        return buildFailedResponse(message);
    }

    private ResponseEntity<BaseResponse> buildFailedResponse(String message) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(false);
        response.setMessage(message);

        return ResponseEntity.ok().body(response);
    }
}
