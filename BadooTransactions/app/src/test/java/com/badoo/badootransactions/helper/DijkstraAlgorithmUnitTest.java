package com.badoo.badootransactions.helper;

import com.badoo.badootransactions.model.ConversionEdge;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (c) 2017 Badoo
 */

public class DijkstraAlgorithmUnitTest {

    @Test
    public void checkConversionRate() {
        HashMap<String, Double> conversions = new HashMap<>();
        conversions.put("USD", 2.0);
        conversions.put("GBP", 1.0);

        ConversionEdge edge = new ConversionEdge();
        edge.setTo("GBP");
        edge.setFrom("USD");
        edge.setRateValue(2.0);
        edge.setRate("2.0");

        ArrayList<ConversionEdge> conversionEdges = new ArrayList<>();
        conversionEdges.add(edge);

        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(conversionEdges);
        dijkstraAlgorithm.execute("USD");
        assertEquals(Double.valueOf(2.0), dijkstraAlgorithm.conversionRate("GBP"));
    }
}
