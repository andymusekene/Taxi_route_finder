package com.group9.taxirouter;

import java.util.*;

public class Graph {
    private Map<String, Node> nodes = new LinkedHashMap<>();
    private Map<String, List<Edge>> adj = new HashMap<>();
    private Map<String, Point> coords = new HashMap<>(); // for visualizer

    public void addNode(Node n, Point p) {
        nodes.put(n.getId(), n);
        adj.putIfAbsent(n.getId(), new ArrayList<>());
        coords.put(n.getId(), p);
    }

    public void addEdge(Edge e) {
        adj.get(e.getSrc()).add(e);
    }

    public Collection<Node> getNodes() { return nodes.values(); }
    public List<Edge> getEdgesFrom(String id) {
        return adj.getOrDefault(id, Collections.emptyList());
    }

    public Node getNode(String id) {
        return nodes.get(id);
    }

    public Point getCoord(String id) { return coords.get(id); }
}
