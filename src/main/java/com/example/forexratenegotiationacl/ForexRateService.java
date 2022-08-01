package com.example.forexratenegotiationacl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ForexRateService {

    Environment environment;

    public ForexRateService(Environment environment) {
        this.environment = environment;
    }

    public ForexRate getForexRateForCurrency(String currency) {
        return getForexRateFromCore(currency);
    }

    private ForexRate getForexRateFromCore(String currency) {


        String twdRateFromPropertyFile = environment.getProperty("ForexRate.01");
        BigDecimal twdRate = new BigDecimal(twdRateFromPropertyFile);

        BigDecimal currencyRate = null;
        if ("99".equals(currency)) {
            currencyRate = BigDecimal.ONE;
        } else {
            String currencyRateFromPropertyFile = environment.getProperty("ForexRate." + currency);

            currencyRate = new BigDecimal(currencyRateFromPropertyFile);
        }

        BigDecimal resultingRate = currencyRate.divide(twdRate, 5, RoundingMode.HALF_UP);
        return ForexRate.builder().toUsdTtb(resultingRate).build();
    }
}
