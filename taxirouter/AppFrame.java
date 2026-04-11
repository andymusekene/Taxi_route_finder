package com.group9.taxirouter;

import java.awt.*;
import javax.swing.*;

public class AppFrame extends JFrame {
    private Graph g;
    private JComboBox<String> sourceCombo;
    private JComboBox<String> destCombo;
    private JTextArea out;
    private RouteVisualizer viz;

    public AppFrame(Graph g) {
        super("Taxi Route Optimizer - Cape Town (Developed by Siya Honono)");
        this.g = g;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel top = new JPanel(new BorderLayout(8,8));
        JPanel controls = new JPanel();
        sourceCombo = new JComboBox<>();
        destCombo = new JComboBox<>();
        for (Node n : g.getNodes()) {
            sourceCombo.addItem(n.getId());
            destCombo.addItem(n.getId());
        }
        controls.add(new JLabel("Source:"));
        controls.add(sourceCombo);
        controls.add(new JLabel("Destination:"));
        controls.add(destCombo);
        JButton find = new JButton("Find Shortest Route");
        JButton clear = new JButton("Clear");
        controls.add(find);
        controls.add(clear);

        out = new JTextArea(4, 60);
        out.setEditable(false);
        JScrollPane outScroll = new JScrollPane(out);

        top.add(controls, BorderLayout.NORTH);
        top.add(outScroll, BorderLayout.CENTER);

        viz = new RouteVisualizer(g);
        getContentPane().setLayout(new BorderLayout(8,8));
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(viz, BorderLayout.CENTER);

        find.addActionListener(e -> {
            String s = (String) sourceCombo.getSelectedItem();
            String d = (String) destCombo.getSelectedItem();
            Dijkstra.Result r = Dijkstra.shortestPath(g, s, d);
            if (r == null) {
                out.setText("No path or invalid nodes.");
                viz.highlightPath(null);
            } else {
                out.setText("Distance: " + r.distance + "\nPath: " + String.join(" -> ", r.path));
                viz.highlightPath(r.path);
            }
        });

        clear.addActionListener(e -> {
            out.setText("");
            viz.highlightPath(null);
        });
    }
}
