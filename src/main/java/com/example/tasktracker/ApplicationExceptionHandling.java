package com.example.tasktracker;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@RestControllerAdvice
public class ApplicationExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> invalidHandling(MethodArgumentNotValidException ex){
        Map<String,String>errorMap=new HashMap();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String,String> dateHandling(HttpMessageNotReadableException ex){
        Map<String,String>errorMap=new HashMap();
        errorMap.put("message", "Please provide valid date.");
        errorMap.put("format1", "yyyy-MM-dd'T'HH:mm:ss.SSS");
        errorMap.put("format2", "yyyy-MM-dd");
        errorMap.put("format3", "EEE, dd MMM yyyy HH:mm:ss zzz");
        
        return errorMap;
    }
    
}

