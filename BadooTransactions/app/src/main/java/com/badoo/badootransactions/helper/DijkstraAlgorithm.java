package com.badoo.badootransactions.helper;

import com.badoo.badootransactions.model.ConversionEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Copyright (c) 2017 Badoo
 */

public class DijkstraAlgorithm {

    private final List<ConversionEdge> edges;
    private Set<String> settledNodes;
    private Set<String> unSettledNodes;
    private Map<String, Double> distance;


    public DijkstraAlgorithm(List<ConversionEdge> edges) {
        this.edges = new ArrayList<>(edges);
    }

    /**
     * Compute all rate conversions from selected currency
     *
     * @param source selected currency
     */
    public void execute(String source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        distance.put(source, 1.0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            String node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    /**
     * @param target selected target currency
     * @return the conversion rate from the source which was executed previously to the selected target
     * currency
     */
    public Double conversionRate(String target) {
        return distance.get(target);
    }

    private void findMinimalDistances(String node) {
        List<String> adjacentNodes = getNeighbors(node);
        for (String target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    * getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        * getDistance(node, target));
                unSettledNodes.add(target);
            }
        }

    }

    private Double getDistance(String node, String target) {
        for (ConversionEdge edge : edges) {
            if (edge.getFrom().equals(node)
                    && edge.getTo().equals(target)) {
                return edge.getRateValue();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<String> getNeighbors(String node) {
        List<String> neighbors = new ArrayList<>();
        for (ConversionEdge edge : edges) {
            if (edge.getFrom().equals(node)
                    && !isSettled(edge.getTo())) {
                neighbors.add(edge.getTo());
            }
        }
        return neighbors;
    }

    private String getMinimum(Set<String> vertexes) {
        String minimum = null;
        for (String vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(String vertex) {
        return settledNodes.contains(vertex);
    }

    private Double getShortestDistance(String destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }
}