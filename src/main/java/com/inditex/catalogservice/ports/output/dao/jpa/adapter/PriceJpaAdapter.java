package com.inditex.catalogservice.ports.output.dao.jpa.adapter;

import com.inditex.catalogservice.commons.exeption.ExceptionEnum;
import com.inditex.catalogservice.commons.exeption.NotFoundException;
import com.inditex.catalogservice.core.domain.PriceDTO;
import com.inditex.catalogservice.core.repository.PricePort;
import com.inditex.catalogservice.ports.output.dao.jpa.mapper.PriceJpaMapper;
import com.inditex.catalogservice.ports.output.dao.jpa.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class PriceJpaAdapter implements PricePort {

    private final PriceRepository priceRepository;

    private final PriceJpaMapper mapper;

    @Override
    public PriceDTO findById(Long id) {
        var price = this.priceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.NOT_FOUND_ERROR));
        return this.mapper.toPriceDTO(price);
    }

    @Override
    public Page findAll(Pageable pageable) {
        return this.priceRepository.findAll(pageable)
                .map(this.mapper::toPriceDTO);
    }

    @Override
    public PriceDTO findByDatesAndProductIdAndBrandId(LocalDateTime applicationDate, Long productId, Long brandId) {
        var prices = this.priceRepository.findByDatesAndProductIdAndBrandId(applicationDate, productId, brandId);
        if (prices.isEmpty()) {
            throw new NotFoundException(ExceptionEnum.NOT_FOUND_ERROR);
        }
        var price = Collections.max(prices, Comparator.comparing(o -> o.getPriority() >= 1));
        return this.mapper.toPriceDTO(price);
    }
}
