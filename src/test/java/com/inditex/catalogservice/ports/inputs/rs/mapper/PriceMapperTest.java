package com.inditex.catalogservice.ports.inputs.rs.mapper;

import com.inditex.catalogservice.core.domain.PriceDTO;
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
