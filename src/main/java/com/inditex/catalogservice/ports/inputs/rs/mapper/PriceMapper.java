package com.inditex.catalogservice.ports.inputs.rs.mapper;

import com.inditex.catalogservice.core.domain.PriceDTO;
import com.inditex.catalogservice.ports.inputs.rs.response.PriceResponse;
import com.inditex.catalogservice.ports.output.dao.jpa.entity.Price;
import org.mapstruct.Mapper;

@Mapper
public interface PriceMapper {

    PriceDTO toPriceDTO(Price price);

    PriceResponse fromPriceDTOToPriceResponse(PriceDTO priceDTO);
}
