package com.group9.taxirouter;

public class Node {
    private String id;
    private String name;
    public Node(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String toString() { return id + " - " + name; }
}
