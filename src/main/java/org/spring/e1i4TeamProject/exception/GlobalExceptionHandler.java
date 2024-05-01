package org.spring.e1i4TeamProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice // Exception 처리
@RestController
public class GlobalExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<?> exceptionHandlerMethod(Exception e){
    String js="<script> alert('"+e.getMessage()+"'); " +
        " history.go(-1);</script>";
    String html="<div>"+js+"</div>";

    return  ResponseEntity.status(HttpStatus.OK).body(html);
  }
}