package com.group9.taxirouter;

import java.util.*;

public class Dijkstra {
    public static class Result {
        public double distance;
        public List<String> path;
        public Result(double distance, List<String> path) {
            this.distance = distance;
            this.path = path;
        }
    }

    public static Result shortestPath(Graph g, String startId, String endId) {
        if (g.getNode(startId) == null || g.getNode(endId) == null) return null;
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        Comparator<String> cmp = Comparator.comparingDouble(dist::get);
        PriorityQueue<String> pq = new PriorityQueue<>(cmp);
        for (var n : g.getNodes()) {
            dist.put(n.getId(), Double.POSITIVE_INFINITY);
            prev.put(n.getId(), null);
        }
        dist.put(startId, 0.0);
        pq.add(startId);
        while (!pq.isEmpty()) {
            String u = pq.poll();
            if (u.equals(endId)) break;
            for (Edge e : g.getEdgesFrom(u)) {
                String v = e.getDst();
                double alt = dist.get(u) + e.getWeight();
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    prev.put(v, u);
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
        if (dist.get(endId).isInfinite()) return null;
        List<String> path = new ArrayList<>();
        for (String at = endId; at != null; at = prev.get(at)) path.add(at);
        Collections.reverse(path);
        return new Result(dist.get(endId), path);
    }
}
