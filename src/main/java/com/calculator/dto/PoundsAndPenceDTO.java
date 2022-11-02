package com.calculator.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
@Builder
public class PoundsAndPenceDTO
{

    private BigDecimal penceSubmitted;
    private String externalID;
    private Map<String, Integer> pounds;
    private Map<String, Integer> pence;
    private Date   dateTime;

}
