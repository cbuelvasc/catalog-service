package com.inditex.catalogservice.core.service;

import com.inditex.catalogservice.core.domain.PriceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IPriceService {

    PriceDTO getPrice(Long id);

    Page<PriceDTO> getAllPrices(Pageable pageable);

    PriceDTO getPriceByDateProductAndBrand(LocalDateTime applicationDate, Long productId, Long brandId);
}
