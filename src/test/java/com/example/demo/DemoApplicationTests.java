package com.example.demo;

import com.example.demo.entity.CoinDesk;
import com.example.demo.repository.CoinDeskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import java.math.BigDecimal;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

  @Autowired
  private DemoApplication app;

  @Description("coindesk api")
  @Test
  void api() throws JsonProcessingException {
    log.info("{}", app.getData());
  }

  @Description("coindesk api 重新組裝")
  @Test
  void newApi() throws JsonProcessingException {
    log.info("{}", app.getNewCoinDesk());
  }

  @Description("查詢")
  @Test
  void query() {
    log.info("{}", app.queryCoin("TWD"));
  }

  @Description("新增幣別")
  @Test
  void create(){
    CoinDesk coinDesk = new CoinDesk();
    coinDesk.setCode("TWD");
    coinDesk.setSymbol("$");
    coinDesk.setRate(BigDecimal.valueOf(1.001));
    coinDesk.setDescription("TAIWAN");
    coinDesk.setRateFloat(BigDecimal.valueOf(1.001));
    app.createCoin(coinDesk);
    this.query();
  }

  @Description("編輯幣別")
  @Test
  void edit(){
    CoinDesk coinDesk = new CoinDesk();
    coinDesk.setCode("TWD");
    coinDesk.setSymbol("$");
    coinDesk.setRate(BigDecimal.valueOf(1.002));
    coinDesk.setDescription("TAIWAN NO 1");
    coinDesk.setRateFloat(BigDecimal.valueOf(1.002));
    app.editCoin(coinDesk);
    this.query();
  }

  @Description("移除幣別")
  @Test
  void remove(){
    CoinDesk coinDesk = new CoinDesk();
    coinDesk.setCode("TWD");
    app.removeCoin(coinDesk);
    this.query();
  }
}
