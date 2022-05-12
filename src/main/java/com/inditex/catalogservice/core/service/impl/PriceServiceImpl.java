package com.inditex.catalogservice.core.service.impl;

import com.inditex.catalogservice.core.domain.PriceDTO;
import com.inditex.catalogservice.core.repository.PricePort;
import com.inditex.catalogservice.core.service.IPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements IPriceService {

    private final PricePort pricePort;

    @Override
    public PriceDTO getPrice(Long id) {
        return this.pricePort.findById(id);
    }

    @Override
    public Page<PriceDTO> getAllPrices(Pageable pageable) {
        return this.pricePort.findAll(pageable);
    }

    @Override
    public PriceDTO getPriceByDateProductAndBrand(LocalDateTime applicationDate, Long productId, Long brandId) {
        return this.pricePort.findByDatesAndProductIdAndBrandId(applicationDate, productId, brandId);
    }
}
