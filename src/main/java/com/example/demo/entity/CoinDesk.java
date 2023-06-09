package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Table(name = "coin_desk")
public class CoinDesk {

  @Id
  @Column(name = "code", columnDefinition = "varchar(4) primary key")
  private String code;
  @Column(name = "symbol", columnDefinition = "varchar(16) not null")
  private String symbol;
  @Column(name = "rate", columnDefinition = "numeric(38,2) not null")
  private BigDecimal rate;
  @Column(name = "description", columnDefinition = "varchar(128)")
  private String description;
  @Column(name = "rate_float", columnDefinition = "numeric(38,2) not null")
  private BigDecimal rateFloat;
}
