package com.group9.taxirouter;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVLoader {
    public static Graph loadGraph(String nodesCsvPath, String edgesCsvPath) throws IOException {
        Graph g = new Graph();
        // load nodes with coords: CSV format id,name,x,y  (coordinates are arbitrary units for map)
        List<String> nlines = Files.readAllLines(Paths.get(nodesCsvPath));
        for (String line : nlines) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#") || line.toLowerCase().startsWith("id,")) continue;
            String[] parts = line.split(",", 4);
            if (parts.length < 4) continue;
            String id = parts[0].trim();
            String name = parts[1].trim();
            double x = Double.parseDouble(parts[2].trim());
            double y = Double.parseDouble(parts[3].trim());
            g.addNode(new Node(id, name), new Point(x, y));
        }
        // load edges
        List<String> elines = Files.readAllLines(Paths.get(edgesCsvPath));
        for (String line : elines) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#") || line.toLowerCase().startsWith("src,")) continue;
            String[] parts = line.split(",", 3);
            if (parts.length < 3) continue;
            String src = parts[0].trim();
            String dst = parts[1].trim();
            double w = Double.parseDouble(parts[2].trim());
            g.addEdge(new Edge(src, dst, w));
            // undirected
            g.addEdge(new Edge(dst, src, w));
        }
        return g;
    }
}
