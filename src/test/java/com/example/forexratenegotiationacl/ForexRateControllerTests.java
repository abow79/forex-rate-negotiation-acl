package com.example.forexratenegotiationacl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ForexRateControllerTests {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ForexRateService forexRateService;

    @BeforeEach
    public void setup(){
    }

    @Test
    public void getForexRateTWD_WillReturn_USDTWDRate() throws Exception {

        ForexRate expected = ForexRate.builder().toUsdTtb(new BigDecimal("0.03348")).build();

        // 定義 Mock 行為: Service 被呼叫 getForexRateForCurrency("99") 時, 會回傳 expected
        given(forexRateService.getForexRateForCurrency("99")).willReturn(expected);

        // 驗證 Controller 行為
        mockMvc.perform(get("/v1/forexrate")
                        .contentType(MediaType.APPLICATION_JSON).queryParam("currency", "99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.toUsdTtb").value(new BigDecimal("0.03348")));

        // 驗證 Mock 確實有被用到: 在整個 Test 的過程中 forexRateService.getForexRateForCurrency("99") 有被呼叫過 1 次
        verify(forexRateService, times(1)).getForexRateForCurrency("99");
    }

}



