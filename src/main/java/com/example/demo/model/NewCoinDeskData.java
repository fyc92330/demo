package com.example.demo.model;

import com.example.demo.enums.CoinEnum;
import lombok.Data;

import java.util.List;

@Data
public class NewCoinDeskData {

  private String time;
  private List<Coin> coins;

  @Data
  public static class Coin {
    private String code;
    private String coinName;
    private String rate;

    public Coin(String code, String rate) {
      this.code = code;
      this.rate = rate;
      this.coinName = CoinEnum.getEnum(code).getCoinName();
    }
  }
}
