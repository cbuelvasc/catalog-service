package com.inditex.catalogservice.ports.inputs.rs.mapper;

import com.inditex.catalogservice.core.domain.PriceDTO;
import com.inditex.catalogservice.ports.inputs.rs.response.PriceResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PriceMapper {

    PriceResponse fromPriceDTOToPriceResponse(PriceDTO priceDTO);
}
