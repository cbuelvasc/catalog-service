package com.inditex.catalogservice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(classes = CatalogServiceApplication.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles({"test"})
@TestPropertySource(locations = "classpath:application-test.yaml")
public class AbstractApplicationTest {
}
