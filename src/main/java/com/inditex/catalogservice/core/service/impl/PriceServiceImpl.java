package com.inditex.catalogservice.core.service.impl;

import com.inditex.catalogservice.commons.exeption.ExceptionEnum;
import com.inditex.catalogservice.commons.exeption.NotFoundException;
import com.inditex.catalogservice.core.domain.PriceDTO;
import com.inditex.catalogservice.core.mapper.PriceCoreMapper;
import com.inditex.catalogservice.core.service.IPriceService;
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
public class PriceServiceImpl implements IPriceService {

    private final PriceRepository priceRepository;

    private final PriceCoreMapper priceCoreMapper;

    @Override
    public PriceDTO getPrice(Long id) {
        return this.priceRepository.findById(id)
                .map(this.priceCoreMapper::toPriceDTO)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.NOT_FOUND_ERROR));
    }

    @Override
    public Page<PriceDTO> getAllPrices(Pageable pageable) {
        return this.priceRepository.findAll(pageable)
                .map(this.priceCoreMapper::toPriceDTO);
    }

    @Override
    public PriceDTO getPriceByDateProductAndBrand(LocalDateTime applicationDate, Long productId, Long brandId) {

        var prices = this.priceRepository.findByDatesAndProductIdAndBrandId(
                applicationDate.toLocalDate().atStartOfDay(), applicationDate, applicationDate, productId, brandId);

        if (prices.isEmpty()) {
            prices = this.priceRepository.findByDateAndProductIdAndBrandId(applicationDate, productId, brandId);
        }

        var price = Collections.max(prices, Comparator.comparing(o -> o.getPriority() >= 1));
        return priceCoreMapper.toPriceDTO(price);
    }
}
