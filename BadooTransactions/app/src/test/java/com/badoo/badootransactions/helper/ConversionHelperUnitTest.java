package com.badoo.badootransactions.helper;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (c) 2017 Badoo
 */

public class ConversionHelperUnitTest {

    @Test
    public void checkConversionTypes() {
        assertEquals("$", ConversionHelper.getType("USD"));
        assertEquals("A$", ConversionHelper.getType("AUD"));
        assertEquals("CA$", ConversionHelper.getType("CAD"));
        assertEquals("£", ConversionHelper.getType("GBP"));
        assertEquals("€", ConversionHelper.getType("EUR"));
    }

    @Test
    public void checkConversion() {
        HashMap<String, Double> conversions = new HashMap<>();
        conversions.put("USD", 2.0);
        assertEquals("20.0", ConversionHelper.getConvertedValue("10", "USD", conversions).toString());
    }
}
