package com.inditex.catalogservice.ports.inputs.rs.controller.impl;

import com.inditex.catalogservice.core.domain.PriceDTO;
import com.inditex.catalogservice.core.service.IPriceService;
import com.inditex.catalogservice.ports.inputs.rs.assembers.PriceAssembler;
import com.inditex.catalogservice.ports.inputs.rs.controller.IPriceController;
import com.inditex.catalogservice.ports.inputs.rs.response.PriceResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/prices")
@AllArgsConstructor
public class PriceController implements IPriceController {

    private final IPriceService priceService;

    private final PriceAssembler priceAssembler;

    private final PagedResourcesAssembler<PriceDTO> pagedResourcesAssembler;

    @Override
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PriceResponse getPriceById(@PathVariable Long id) {
        log.info("REST request to get price : {}", id);
        return this.priceAssembler.toModel(this.priceService.getPrice(id));
    }

    @Override
    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<PriceResponse> getAllPrices(Pageable pageable) {
        log.info("REST request to get all prices");
        final Page<PriceDTO> page = this.priceService.getAllPrices(pageable);
        return this.pagedResourcesAssembler.toModel(page, priceAssembler);
    }

    @Override
    @GetMapping(value = "/currentPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PriceResponse getPriceByDateProductAndBrand(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate,
                                                       @RequestParam Long productId,
                                                       @RequestParam Long brandId) {
        log.info("REST request to get price with params : application date {}, product {} and  brand {}", applicationDate.toString(), productId, brandId);
        var priceDTO = this.priceService.getPriceByDateProductAndBrand(applicationDate, productId, brandId);
        return this.priceAssembler.toModel(priceDTO);
    }
}
