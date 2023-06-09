package com.example.demo.exception;

import lombok.Getter;

@Getter
public class DataInvalidException extends RuntimeException {

  private final String errorMsg;
  public DataInvalidException(String error){
    this.errorMsg = error;
  }
}
