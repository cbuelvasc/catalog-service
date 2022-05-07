package com.inditex.catalogservice.core.service;

import com.inditex.catalogservice.commons.exeption.ExceptionEnum;
import com.inditex.catalogservice.commons.exeption.NotFoundException;
import com.inditex.catalogservice.core.service.impl.PriceServiceImpl;
import com.inditex.catalogservice.ports.inputs.rs.mapper.PriceMapper;
import com.inditex.catalogservice.ports.output.dao.jpa.entity.Price;
import com.inditex.catalogservice.ports.output.dao.jpa.repository.PriceRepository;
import com.inditex.catalogservice.util.PriceDTOTestUtil;
import com.inditex.catalogservice.util.PriceTestUtil;
import com.inditex.catalogservice.util.RandomTestUtil;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    private IPriceService priceService;

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private Page<Price> pricePage;

    private static PriceMapper mapper;

    private static EasyRandom easyRandom;

    @BeforeEach
    void setup() {
        mapper = Mappers.getMapper(PriceMapper.class);
        easyRandom = RandomTestUtil.buildFastEasyRandom();
        priceService = new PriceServiceImpl(priceRepository, mapper);
    }

    @Test
    void getPrice_ValidValues_Success() {
        var price = easyRandom.nextObject(Price.class);
        var priceId = easyRandom.nextObject(Long.class);

        when(this.priceRepository.findById(priceId))
                .thenReturn(Optional.of(price));

        this.priceService.getPrice(priceId);
        verify(this.priceRepository).findById(priceId);
    }

    @Test
    void getPrice_EmptyValues_Error() {
        var priceId = easyRandom.nextObject(Long.class);

        when(this.priceRepository.findById(priceId))
                .thenReturn(Optional.empty());

        var exception = assertThrows(NotFoundException.class,
                () -> this.priceService.getPrice(priceId));

        verify(this.priceRepository).findById(priceId);
        assertThat(exception.getErrorCode()).isEqualTo(ExceptionEnum.NOT_FOUND_ERROR.getValue());
    }

    @Test
    void getAllPrices_ValidValues_Success() {
        var pageable = PageRequest.of(0, 8);

        when(this.priceRepository.findAll(pageable))
                .thenReturn(pricePage);

        this.priceService.getAllPrices(pageable);
        verify(this.priceRepository).findAll(pageable);
    }

    @Test
    void getPriceByDateProductAndBrand_ValidValues_Success() {
        var applicationDate = LocalDateTime.parse("2020-06-16 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var priceDTO = PriceDTOTestUtil.getMockRequest();
        var price = PriceTestUtil.getMockRequest();
        var productId = 34455L;
        var brandId = 1L;

        when(this.priceRepository.findByDatesAndProductIdAndBrandId(applicationDate, productId, brandId))
                .thenReturn(Arrays.asList(price));

        this.priceService.getPriceByDateProductAndBrand(applicationDate, productId, brandId);
        verify(this.priceRepository).findByDatesAndProductIdAndBrandId(applicationDate, productId, brandId);
        assertThat(priceDTO).isEqualTo(mapper.toPriceDTO(price));
    }

    @Test
    void getPriceByDateProductAndBrand_ValidValues_Error() {
        var applicationDate =
                LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var priceDTO = PriceDTOTestUtil.getMockRequest();
        var price = PriceTestUtil.getMockRequest();
        var productId = 34455L;
        var brandId = 1L;

        when(this.priceRepository.findByDatesAndProductIdAndBrandId(applicationDate, productId, brandId))
                .thenReturn(Arrays.asList());

        var exception = assertThrows(NotFoundException.class,
                () -> this.priceService.getPriceByDateProductAndBrand(applicationDate, productId, brandId));

        verify(this.priceRepository).findByDatesAndProductIdAndBrandId(applicationDate, productId, brandId);
        assertThat(exception.getErrorCode()).isEqualTo(ExceptionEnum.NOT_FOUND_ERROR.getValue());
    }
}
