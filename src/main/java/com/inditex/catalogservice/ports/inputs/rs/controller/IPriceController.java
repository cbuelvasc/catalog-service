package com.inditex.catalogservice.ports.inputs.rs.controller;

import com.inditex.catalogservice.ports.inputs.rs.response.PriceResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import java.time.LocalDateTime;

public interface IPriceController {

    PriceResponse getPriceById(Long id);

    PagedModel<PriceResponse> getAllPrices(Pageable pageable);

    PriceResponse getPriceByDateProductAndBrand(LocalDateTime applicationDate, Long productId, Long brandId);
}
