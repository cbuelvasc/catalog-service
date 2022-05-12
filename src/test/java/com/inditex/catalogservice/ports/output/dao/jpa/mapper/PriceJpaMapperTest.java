package com.inditex.catalogservice.ports.output.dao.jpa.mapper;

import com.inditex.catalogservice.ports.output.dao.jpa.entity.Price;
import com.inditex.catalogservice.util.RandomTestUtil;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceJpaMapperTest {

    public static PriceJpaMapper mapper;

    private static EasyRandom easyRandom;

    @BeforeAll
    static void init() {
        mapper = Mappers.getMapper(PriceJpaMapper.class);
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
}
