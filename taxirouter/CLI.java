package com.group9.taxirouter;

import java.util.Scanner;

public class CLI {
    public static void run(Graph g) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu: 1) List nodes  2) Find shortest path  3) Exit");
            System.out.print("Choose: ");
            String c = sc.nextLine().trim();
            if (c.equals("1")) {
                g.getNodes().forEach(n -> System.out.println(n.getId() + ": " + n.getName()));
            } else if (c.equals("2")) {
                System.out.print("Source node id: ");
                String s = sc.nextLine().trim();
                System.out.print("Destination node id: ");
                String d = sc.nextLine().trim();
                Dijkstra.Result res = Dijkstra.shortestPath(g, s, d);
                if (res == null) {
                    System.out.println("No path or invalid nodes.");
                } else {
                    System.out.println("Distance: " + res.distance);
                    System.out.println("Path: " + String.join(" -> ", res.path));
                }
            } else if (c.equals("3")) {
                System.out.println("Exiting CLI.");
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
