package com.group9.taxirouter;

public class Edge {
    private String src;
    private String dst;
    private double weight;
    public Edge(String src, String dst, double weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }
    public String getSrc() { return src; }
    public String getDst() { return dst; }
    public double getWeight() { return weight; }
    public String toString() { return src + "->" + dst + "(" + weight + ")"; }
}
