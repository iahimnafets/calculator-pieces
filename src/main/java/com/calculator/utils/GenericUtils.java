package com.calculator.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GenericUtils {

    private static final int [] banknotes = new int[] { 50, 20, 10, 5, 2, 1 };

    /**
     * Method that calculates how many banknotes/coins to return
     * based on the baconote values contained in "banknotes" array
     *
     * @param amount
     * @return
     */
    public static Map<String,Integer> calculateAndTransformPence( Integer amount ){
        LinkedHashMap<String,Integer> result = new LinkedHashMap();
        for(Integer banknote : banknotes) {
            result.put (banknote.toString(), amount / banknote);
            amount = amount % banknote;
        }
        return result;
    }

}
