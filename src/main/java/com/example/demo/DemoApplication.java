package com.example.demo;

import com.example.demo.entity.CoinDesk;
import com.example.demo.exception.DataInvalidException;
import com.example.demo.model.ApiResponseModel;
import com.example.demo.model.CoinDeskData;
import com.example.demo.model.NewCoinDeskData;
import com.example.demo.repository.CoinDeskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/coinDesk")
@SpringBootApplication
public class DemoApplication {

  private final CoinDeskRepository coinDeskRepository;
  private final ObjectMapper objectMapper;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @GetMapping("/api")
  public ApiResponseModel<?> getNewCoinDesk() throws JsonProcessingException {
    CoinDeskData data = this.getData();
    return new ApiResponseModel<>(this.getNewData(data));
  }

  public CoinDeskData getData() throws JsonProcessingException {
    return objectMapper.readValue(
        new RestTemplate().getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class),
        CoinDeskData.class
    );
  }

  public NewCoinDeskData getNewData(CoinDeskData data) {
    LocalDateTime dateTime = LocalDateTime.parse(data.getTime().getUpdatedISO(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    String time = dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh/mm/ss"));

    CoinDeskData.Bpi.BpiDesc usd = data.getBpi().getUsd();
    CoinDeskData.Bpi.BpiDesc gbp = data.getBpi().getGbp();
    CoinDeskData.Bpi.BpiDesc eur = data.getBpi().getEur();

    NewCoinDeskData newData = new NewCoinDeskData();
    newData.setTime(time);
    newData.setCoins(Arrays.asList(
        new NewCoinDeskData.Coin(usd.getCode(), usd.getRate()),
        new NewCoinDeskData.Coin(gbp.getCode(), gbp.getRate()),
        new NewCoinDeskData.Coin(eur.getCode(), eur.getRate())
    ));
    return newData;
  }

  @GetMapping("/coin/{code}")
  public ApiResponseModel<?> queryCoin(@PathVariable("code") String code) {
    CoinDesk coinDesk = coinDeskRepository.findById(code)
        .orElseThrow(() -> new DataInvalidException("資料不存在"));
    return new ApiResponseModel<>(coinDesk);
  }

  @PostMapping("/coin")
  public ApiResponseModel<?> createCoin(@RequestBody CoinDesk coinDesk) {
    if (this.isExists(coinDesk.getCode())) {
      throw new DataInvalidException("資料已存在");
    }
    coinDeskRepository.save(coinDesk);
    return new ApiResponseModel<>();
  }

  @PutMapping("/coin")
  public ApiResponseModel<?> editCoin(@RequestBody CoinDesk coinDesk) {
    if (!this.isExists(coinDesk.getCode())) {
      throw new DataInvalidException("資料不存在");
    }
    coinDeskRepository.save(coinDesk);
    return new ApiResponseModel<>();
  }

  @DeleteMapping("/coin")
  public ApiResponseModel<?> removeCoin(@RequestBody CoinDesk coinDesk) {
    if (!this.isExists(coinDesk.getCode())) {
      throw new DataInvalidException("資料不存在");
    }
    coinDeskRepository.deleteById(coinDesk.getCode());
    return new ApiResponseModel<>();
  }

  private boolean isExists(String code) {
    return coinDeskRepository.findById(code).isPresent();
  }
}
