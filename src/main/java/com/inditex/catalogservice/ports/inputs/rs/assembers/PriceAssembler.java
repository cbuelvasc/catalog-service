package com.inditex.catalogservice.ports.inputs.rs.assembers;

import com.inditex.catalogservice.core.domain.PriceDTO;
import com.inditex.catalogservice.ports.inputs.rs.controller.impl.PriceController;
import com.inditex.catalogservice.ports.inputs.rs.mapper.PriceMapper;
import com.inditex.catalogservice.ports.inputs.rs.response.PriceResponse;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PriceAssembler extends RepresentationModelAssemblerSupport<PriceDTO, PriceResponse> {

    private final PriceMapper mapper;

    public PriceAssembler(PriceMapper mapper) {
        super(PriceController.class, PriceResponse.class);
        this.mapper = mapper;
    }

    @Override
    public PriceResponse toModel(PriceDTO priceDTO) {
        return this.mapper.fromPriceDTOToPriceResponse(priceDTO)
                .add(linkTo(methodOn(PriceController.class).getPriceById(priceDTO.getId()))
                        .withSelfRel());
    }
}
