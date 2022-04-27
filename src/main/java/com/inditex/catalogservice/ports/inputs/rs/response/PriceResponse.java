package com.inditex.catalogservice.ports.inputs.rs.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "prices", itemRelation = "price")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceResponse extends RepresentationModel<PriceResponse> {

    private Long id;

    private Long brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long productId;

    private Integer priority;

    private BigDecimal price;

    private String currency;
}
