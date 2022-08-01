package com.example.forexratenegotiationacl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForexRate {
    @JsonProperty("toUsdTtb")
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private BigDecimal toUsdTtb;
}
