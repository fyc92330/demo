package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoinDeskData {

  private Time time;

  private String disclaimer;

  private String chartName;

  private Bpi bpi;

  @Data
  public static class Time {
    private String updated;

    private String updatedISO;

    private String updateduk;
  }

  @Data
  public static class Bpi {
    @JsonProperty("USD")
    private BpiDesc usd;
    @JsonProperty("GBP")
    private BpiDesc gbp;
    @JsonProperty("EUR")
    private BpiDesc eur;

    @Data
    public static class BpiDesc {
      private String code;
      private String symbol;
      private String rate;
      private String description;
      private float rate_float;
    }
  }
}
