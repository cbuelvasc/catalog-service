package com.inditex.catalogservice.core.repository;

import com.inditex.catalogservice.core.domain.PriceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PricePort {

    PriceDTO findById(Long id);

    Page findAll(Pageable pageable);

    PriceDTO findByDatesAndProductIdAndBrandId(LocalDateTime applicationDate, Long productId, Long brandId);
}
