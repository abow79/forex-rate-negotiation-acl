package com.example.forexratenegotiationacl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1")
public class ForexRateController {
    ForexRateService forexRateService;

    public ForexRateController(ForexRateService forexRateService){
        this.forexRateService=forexRateService;
    }

    @GetMapping("/forexrate")
    public ForexRate getForexRate(@RequestParam(name="currency") String currency){
        return forexRateService.getForexRateForCurrency(currency);
    }

}
