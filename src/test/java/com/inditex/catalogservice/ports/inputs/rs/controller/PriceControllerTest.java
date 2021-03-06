package com.inditex.catalogservice.ports.inputs.rs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.catalogservice.config.MessageSourceConfiguration;
import com.inditex.catalogservice.config.exeption.handler.ExceptionMessage;
import com.inditex.catalogservice.core.domain.PriceDTO;
import com.inditex.catalogservice.core.service.IPriceService;
import com.inditex.catalogservice.ports.inputs.rs.assembers.PriceAssembler;
import com.inditex.catalogservice.ports.inputs.rs.controller.impl.PriceController;
import com.inditex.catalogservice.util.PriceDTOTestUtil;
import com.inditex.catalogservice.util.RandomTestUtil;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({ExceptionMessage.class, MessageSourceConfiguration.class})
@WebMvcTest(PriceController.class)
public class PriceControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private IPriceService priceService;

    @MockBean
    private PriceAssembler priceAssembler;

    @MockBean
    private PagedResourcesAssembler<PriceDTO> pagedResourcesAssembler;

    @MockBean
    private Pageable pageableMock;

    @MockBean
    private Page<PriceDTO> priceDTOPage;

    private static EasyRandom easyRandom;

    @BeforeEach
    void setUpMvcContext() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
        easyRandom = RandomTestUtil.buildFastEasyRandom();
    }

    @Test
    void getPriceById_Success() throws Exception {
        var priceDTO = easyRandom.nextObject(PriceDTO.class);

        when(priceService.getPrice(priceDTO.getId()))
                .thenReturn(priceDTO);

        this.mockMvc.perform(get("/prices/{id}", priceDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAllPrices_Success() throws Exception {
        var pageable = PageRequest.of(0, 8);

        when(priceService.getAllPrices(pageable))
                .thenReturn(priceDTOPage);

        mockMvc.perform(get("/prices/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getPriceByDateProductAndBrand_ScenarioOne_Success() throws Exception {
        var dateTime = "2020-06-14 10:00:00";
        var applicationDate = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var priceDTO = PriceDTOTestUtil.getMockRequestScenarioOne();
        Long productId = 34455L;
        Long brandId = 1L;

        given(priceService.getPriceByDateProductAndBrand(applicationDate, productId, brandId))
                .willReturn(priceDTO);

        mockMvc.perform(get("/prices/currentPrice")
                        .param("applicationDate", dateTime)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getPriceByDateProductAndBrand_ScenarioTwo_Success() throws Exception {
        var dateTime = "2020-06-14 16:00:00";
        var applicationDate = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var priceDTO = PriceDTOTestUtil.getMockRequestScenarioTwo();
        Long productId = 34455L;
        Long brandId = 1L;

        given(priceService.getPriceByDateProductAndBrand(applicationDate, productId, brandId))
                .willReturn(priceDTO);

        mockMvc.perform(get("/prices/currentPrice")
                        .param("applicationDate", dateTime)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getPriceByDateProductAndBrand_ScenarioThree_Success() throws Exception {
        var dateTime = "2020-06-14 21:00:00";
        var applicationDate = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var priceDTO = PriceDTOTestUtil.getMockRequestScenarioThree();
        Long productId = 34455L;
        Long brandId = 1L;

        given(priceService.getPriceByDateProductAndBrand(applicationDate, productId, brandId))
                .willReturn(priceDTO);

        mockMvc.perform(get("/prices/currentPrice")
                        .param("applicationDate", dateTime)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getPriceByDateProductAndBrand_ScenarioFour_Success() throws Exception {
        var dateTime = "2020-06-15 10:00:00";
        var applicationDate = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var priceDTO = PriceDTOTestUtil.getMockRequestScenarioFour();
        Long productId = 34455L;
        Long brandId = 1L;

        given(priceService.getPriceByDateProductAndBrand(applicationDate, productId, brandId))
                .willReturn(priceDTO);

        mockMvc.perform(get("/prices/currentPrice")
                        .param("applicationDate", dateTime)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getPriceByDateProductAndBrand_ScenarioFive_Success() throws Exception {
        var dateTime = "2020-06-16 21:00:00";
        var applicationDate = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        var priceDTO = PriceDTOTestUtil.getMockRequestScenarioFive();
        Long productId = 34455L;
        Long brandId = 1L;

        given(priceService.getPriceByDateProductAndBrand(applicationDate, productId, brandId))
                .willReturn(priceDTO);

        mockMvc.perform(get("/prices/currentPrice")
                        .param("applicationDate", dateTime)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
