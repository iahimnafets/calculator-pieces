package com.calculator.controller;


import com.calculator.dto.Response;
import com.calculator.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;


@RestController
@RequestMapping( "/api" )
@Slf4j
public class CalculateController
{

    private final Service service;

    @Autowired
    public CalculateController(final Service service )
    {
        this.service = service;
    }


    @Operation( summary =  "transform pence in pounds and pence" )
    @GetMapping( value = "/transform-pence" )
    public ResponseEntity<Response> transformPenceInPoundsAndPence(
                       @RequestParam( name = "pence",  required = true ) final BigDecimal pence,
                       @RequestParam( name = "externalId",  required = true ) final String externalId)
    {
        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK)
                        .data(Map.of("result", service.transformPence( pence, externalId ) ))
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }


}
