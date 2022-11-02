package com.calculator.service;

import com.calculator.dto.PoundsAndPenceDTO;
import com.calculator.exception.ApiRequestException;
import com.calculator.exception.MessageError;
import com.calculator.utils.GenericUtils;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;


@org.springframework.stereotype.Service
@Slf4j
public class Service {


    public PoundsAndPenceDTO transformPence(BigDecimal pence, String externalId) {
        log.info("transformPence-RUN  pence: {}, externalId: {}", pence, externalId );

        if(Objects.isNull(pence) || Objects.isNull(externalId)  ){
            throw new ApiRequestException(MessageError.ID_AND_PENCE_ARE_MANDATORY.getMessage());
        }
        if( pence.remainder(BigDecimal.ONE).movePointRight(pence.scale()).abs().intValue() != 0 ){
            throw new ApiRequestException( MessageError.PENCE_NO_DECIMALS.getMessage() );
        }

        // trasform pence in pounts, the result can have decimals,
        // I calculate the pounds and then also the decimals separately
        BigDecimal poundsAndPence = pence.divide( new BigDecimal(100) );

        // I only keep decimals without pounts
        BigDecimal penceRes =
                poundsAndPence.remainder(BigDecimal.ONE).movePointRight(poundsAndPence.scale()).abs();
        // I only keep pounts without decimals
        BigInteger poundsRes = new BigInteger( poundsAndPence.toBigInteger().toString() );

        log.info(" We have poundsAndPence: {}", poundsAndPence);

        // He created my result and I do the calculations
        PoundsAndPenceDTO result = PoundsAndPenceDTO.builder()
                .externalID(externalId)
                .penceSubmitted(pence)
                .pounds( GenericUtils.calculateAndTransformPence( poundsRes.intValue() ) ) // calculate pounds
                .pence( GenericUtils.calculateAndTransformPence( penceRes.intValue() ))   // calculate pence
                .dateTime( new Date() )
                .build();

        log.info(" result: {}", result);
        return result;
    }



}
