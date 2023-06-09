package com.example.demo.exception;

import com.example.demo.model.ApiResponseModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DataInvalidException.class)
  private ApiResponseModel<?> handler(DataInvalidException e){
    return new ApiResponseModel<>(e.getErrorMsg());
  }
}
