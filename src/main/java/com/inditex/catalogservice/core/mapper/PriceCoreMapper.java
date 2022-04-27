package com.inditex.catalogservice.core.mapper;

import com.inditex.catalogservice.core.domain.PriceDTO;
import com.inditex.catalogservice.ports.output.dao.jpa.entity.Price;
import org.mapstruct.Mapper;

@Mapper
public interface PriceCoreMapper {

    PriceDTO toPriceDTO(Price price);
}
