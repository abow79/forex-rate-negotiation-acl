package com.example.forexratenegotiationacl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ForexRateServiceTests {


    ForexRateService forexRateService;

    @Autowired
    Environment environment;

    @BeforeEach
    public void setup(){
        forexRateService = new ForexRateService(environment);
    }

    @Test
    public void getForexRateTWD_WillReturn_TWDtoUSDRate() {

        ForexRate expected = ForexRate.builder().toUsdTtb(new BigDecimal("0.03348")).build();

        ForexRate actual = forexRateService.getForexRateForCurrency("99");

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void getForexRate_WillReturn_EURtoUSDRate() {
        ForexRate expected = ForexRate.builder().toUsdTtb(new BigDecimal("1.01701")).build();

        ForexRate actual = forexRateService.getForexRateForCurrency("21");

        assertThat(actual).isEqualTo(expected);
    }
}
