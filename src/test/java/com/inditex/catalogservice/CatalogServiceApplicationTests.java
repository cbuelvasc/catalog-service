package com.inditex.catalogservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CatalogServiceApplicationTests extends AbstractApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads_success() {
        var crossBorderApplication = this.applicationContext.getBean(CatalogServiceApplication.class);

        assertThat(crossBorderApplication).isNotNull();
    }
}