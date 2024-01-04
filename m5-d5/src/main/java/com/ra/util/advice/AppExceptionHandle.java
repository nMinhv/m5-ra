package com.ra.util.advice;

import com.ra.util.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandle extends ResponseEntityExceptionHandler {


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> invalidRequest(MethodArgumentNotValidException e) {
//        Map<String, String> errMap = new HashMap<>();
//        e.getFieldErrors().forEach(Error -> {
//            errMap.put(Error.getField(), Error.getDefaultMessage());
//        });
//        return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
//    }
    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static String userException(UserException userException){
        return userException.getMessage();
    }

}
