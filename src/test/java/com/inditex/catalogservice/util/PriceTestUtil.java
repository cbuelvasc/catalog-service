package com.inditex.catalogservice.util;

import com.inditex.catalogservice.ports.output.dao.jpa.entity.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PriceTestUtil {

    public static Price getMockRequest() {
        var price = new Price();
        price.setId(4L);
        price.setBrandId(1L);
        price.setStartDate(LocalDateTime.parse("2020-06-15 16:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        price.setEndDate(LocalDateTime.parse("2020-12-31 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        price.setProductId(35455L);
        price.setProductId(1L);
        price.setPrice(BigDecimal.valueOf(38.95));
        price.setCurrency("EUR");
        return price;
    }
}
