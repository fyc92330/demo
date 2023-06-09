package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApiResponseModel<R> {

  private R data;
  private Integer code = 200;
  private String msg = "OK!";

  public ApiResponseModel(R data){
    this.data = data;
  }

  public ApiResponseModel(String error){
    this.code = 400;
    this.msg = error;
  }
}
