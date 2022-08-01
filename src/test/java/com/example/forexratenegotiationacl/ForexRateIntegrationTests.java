package com.example.forexratenegotiationacl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ForexRateIntegrationTests {

    @LocalServerPort
    int localPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void getTWDtoUSDRate_WillReturn_ForexRateOfTWDtoUSD() {
        // Arrange
        ForexRate expectedForexRate = ForexRate.builder().toUsdTtb(new BigDecimal("0.03348")).build();

        String baseURI = "http://localhost:" + localPort;

        // Act

        ForexRate entity = testRestTemplate.getForEntity(baseURI + "/v1/forexrate?currency=99", ForexRate.class).getBody();

        // Assert

        assertThat(entity).isNotNull();
        assertThat(entity).isEqualTo(expectedForexRate);
    }
}
