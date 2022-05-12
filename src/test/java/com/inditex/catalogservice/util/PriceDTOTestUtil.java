package com.inditex.catalogservice.util;

import com.inditex.catalogservice.core.domain.PriceDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PriceDTOTestUtil {

    public static PriceDTO getMockRequest() {
        var priceDTO = new PriceDTO();
        priceDTO.setId(4L);
        priceDTO.setBrandId(1L);
        priceDTO.setStartDate(LocalDateTime.parse("2020-06-15 16:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setEndDate(LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setProductId(35455L);
        priceDTO.setProductId(1L);
        priceDTO.setPrice(BigDecimal.valueOf(38.95));
        priceDTO.setCurrency("EUR");
        return priceDTO;
    }

    public static PriceDTO getMockRequestScenarioOne() {
        var priceDTO = new PriceDTO();
        priceDTO.setId(1L);
        priceDTO.setBrandId(1L);
        priceDTO.setStartDate(LocalDateTime.parse("2020-06-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setEndDate(LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setProductId(35455L);
        priceDTO.setPriority(0);
        priceDTO.setPrice(BigDecimal.valueOf(35.50));
        priceDTO.setCurrency("EUR");
        return priceDTO;
    }

    public static PriceDTO getMockRequestScenarioTwo() {
        var priceDTO = new PriceDTO();
        priceDTO.setId(2L);
        priceDTO.setBrandId(1L);
        priceDTO.setStartDate(LocalDateTime.parse("2020-06-14 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setEndDate(LocalDateTime.parse("2020-06-14 18:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setProductId(35455L);
        priceDTO.setPriority(1);
        priceDTO.setPrice(BigDecimal.valueOf(55.45));
        priceDTO.setCurrency("EUR");
        return priceDTO;
    }

    public static PriceDTO getMockRequestScenarioThree() {
        var priceDTO = new PriceDTO();
        priceDTO.setId(1L);
        priceDTO.setBrandId(1L);
        priceDTO.setStartDate(LocalDateTime.parse("2020-06-14 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setEndDate(LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setProductId(35455L);
        priceDTO.setPriority(0);
        priceDTO.setPrice(BigDecimal.valueOf(35.50));
        priceDTO.setCurrency("EUR");
        return priceDTO;
    }

    public static PriceDTO getMockRequestScenarioFour() {
        var priceDTO = new PriceDTO();
        priceDTO.setId(3L);
        priceDTO.setBrandId(1L);
        priceDTO.setStartDate(LocalDateTime.parse("2020-06-15 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setEndDate(LocalDateTime.parse("2020-06-15 11:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setProductId(35455L);
        priceDTO.setPriority(1);
        priceDTO.setPrice(BigDecimal.valueOf(30.50));
        priceDTO.setCurrency("EUR");
        return priceDTO;
    }

    public static PriceDTO getMockRequestScenarioFive() {
        var priceDTO = new PriceDTO();
        priceDTO.setId(3L);
        priceDTO.setBrandId(1L);
        priceDTO.setStartDate(LocalDateTime.parse("2020-06-15 16:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setEndDate(LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        priceDTO.setProductId(35455L);
        priceDTO.setPriority(1);
        priceDTO.setPrice(BigDecimal.valueOf(38.95));
        priceDTO.setCurrency("EUR");
        return priceDTO;
    }
}
