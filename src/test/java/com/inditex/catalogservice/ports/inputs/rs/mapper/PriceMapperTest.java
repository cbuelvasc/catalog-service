package com.inditex.catalogservice.ports.inputs.rs.mapper;

import com.inditex.catalogservice.core.domain.PriceDTO;
import com.inditex.catalogservice.ports.output.dao.jpa.entity.Price;
import com.inditex.catalogservice.util.RandomTestUtil;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceMapperTest {

    public static PriceMapper mapper;

    private static EasyRandom easyRandom;

    @BeforeAll
    static void init() {
        mapper = Mappers.getMapper(PriceMapper.class);
        easyRandom = RandomTestUtil.buildFastEasyRandom();
    }

    @Test
    void toPriceDTO_AllValues_Success() {
        var price = easyRandom.nextObject(Price.class);
        var priceDTO = mapper.toPriceDTO(price);

        assertThat(priceDTO)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringAllOverriddenEquals()
                .isEqualTo(price);
    }

    @Test
    void toPriceDTO_NullValues_success() {
        var priceDTO = mapper.toPriceDTO(null);

        assertThat(priceDTO).isNull();
    }

    @Test
    void fromPriceDTOToPriceResponse_AllValues_Success() {
        var priceDTO = easyRandom.nextObject(PriceDTO.class);
        var priceResponse = mapper.fromPriceDTOToPriceResponse(priceDTO);

        assertThat(priceResponse)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringAllOverriddenEquals()
                .isEqualTo(priceResponse);
    }

    @Test
    void fromPriceDTOToPriceResponse_NullValues_success() {
        var priceResponse = mapper.fromPriceDTOToPriceResponse(null);

        assertThat(priceResponse).isNull();
    }
}
