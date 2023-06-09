package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CoinEnum {

  USD("美金"),
  GBP("英鎊"),
  EUR("歐元");

  private final String CoinName;

  public static CoinEnum getEnum(String code) {
    return Arrays.stream(values())
        .filter(e -> e.name().equalsIgnoreCase(code))
        .findAny()
        .orElseThrow(() -> new EnumConstantNotPresentException(CoinEnum.class, code));
  }
}
