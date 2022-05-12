package com.inditex.catalogservice.core.service;

import com.inditex.catalogservice.commons.exeption.NotFoundException;
import com.inditex.catalogservice.core.domain.PriceDTO;
import com.inditex.catalogservice.core.repository.PricePort;
import com.inditex.catalogservice.core.service.impl.PriceServiceImpl;
import com.inditex.catalogservice.util.PriceDTOTestUtil;
import com.inditex.catalogservice.util.RandomTestUtil;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    private IPriceService priceService;

    @Mock
    private PricePort pricePort;

    @Mock
    private Page<PriceDTO> pricePage;

    private static EasyRandom easyRandom;

    @BeforeEach
    void setup() {
        easyRandom = RandomTestUtil.buildFastEasyRandom();
        priceService = new PriceServiceImpl(pricePort);
    }

    @Test
    void getPrice_ValidValues_Success() {
        var price = easyRandom.nextObject(PriceDTO.class);
        var priceId = easyRandom.nextObject(Long.class);

        when(this.pricePort.findById(priceId))
                .thenReturn(price);

        this.priceService.getPrice(priceId);
        verify(this.pricePort).findById(priceId);
    }

    @Test
    void getPrice_EmptyValues_Error() {
        var priceId = easyRandom.nextObject(Long.class);

        when(this.pricePort.findById(priceId))
                .thenThrow(NotFoundException.class);

        var exception = assertThrows(NotFoundException.class,
                () -> this.priceService.getPrice(priceId));

        verify(this.pricePort).findById(priceId);
        assertThat(exception.getClass()).isEqualTo(NotFoundException.class);
    }

    @Test
    void getAllPrices_ValidValues_Success() {
        var pageable = PageRequest.of(0, 8);

        when(this.pricePort.findAll(pageable))
                .thenReturn(pricePage);

        this.priceService.getAllPrices(pageable);
        verify(this.pricePort).findAll(pageable);
    }

    @Test
    void getPriceByDateProductAndBrand_ValidValues_Success() {
        var applicationDate = LocalDateTime.parse("2020-06-16 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var priceDTO = PriceDTOTestUtil.getMockRequest();
        var price = PriceDTOTestUtil.getMockRequest();
        var productId = 34455L;
        var brandId = 1L;

        when(this.pricePort.findByDatesAndProductIdAndBrandId(applicationDate, productId, brandId))
                .thenReturn(price);

        this.priceService.getPriceByDateProductAndBrand(applicationDate, productId, brandId);
        verify(this.pricePort).findByDatesAndProductIdAndBrandId(applicationDate, productId, brandId);
        assertThat(priceDTO).isEqualTo(price);
    }

    @Test
    void getPriceByDateProductAndBrand_ValidValues_Error() {
        var applicationDate =
                LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var priceDTO = PriceDTOTestUtil.getMockRequest();
        var productId = 34455L;
        var brandId = 1L;

        when(this.pricePort.findByDatesAndProductIdAndBrandId(applicationDate, productId, brandId))
                .thenThrow(NotFoundException.class);

        var exception = assertThrows(NotFoundException.class,
                () -> this.priceService.getPriceByDateProductAndBrand(applicationDate, productId, brandId));

        verify(this.pricePort).findByDatesAndProductIdAndBrandId(applicationDate, productId, brandId);
        assertThat(exception.getClass()).isEqualTo(NotFoundException.class);
    }
}
