package com.group9.taxirouter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RouteVisualizer extends JPanel {
    private Graph g;
    private java.util.List<String> highlightPath = null;
    private double minX=Double.MAX_VALUE, minY=Double.MAX_VALUE, maxX=Double.MIN_VALUE, maxY=Double.MIN_VALUE;

    public RouteVisualizer(Graph g) {
        this.g = g;
        setPreferredSize(new Dimension(800, 500));
        computeBounds();
    }

    private void computeBounds() {
        for (Node n : g.getNodes()) {
            Point p = g.getCoord(n.getId());
            if (p == null) continue;
            if (p.x < minX) minX = p.x;
            if (p.y < minY) minY = p.y;
            if (p.x > maxX) maxX = p.x;
            if (p.y > maxY) maxY = p.y;
        }
        if (minX==Double.MAX_VALUE) { minX=0; minY=0; maxX=100; maxY=100; }
    }

    public void highlightPath(List<String> path) {
        this.highlightPath = path;
        repaint();
    }

    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g2 = (Graphics2D) g0;
        int w = getWidth(), h = getHeight();
        double scaleX = (w-80) / (maxX - minX);
        double scaleY = (h-80) / (maxY - minY);
        double scale = Math.min(scaleX, scaleY);
        // draw edges
        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.LIGHT_GRAY);
        for (Node n : g.getNodes()) {
            Point p1 = g.getCoord(n.getId());
            for (Edge e : g.getEdgesFrom(n.getId())) {
                Point p2 = g.getCoord(e.getDst());
                int x1 = (int)((p1.x - minX) * scale) + 40;
                int y1 = (int)((p1.y - minY) * scale) + 40;
                int x2 = (int)((p2.x - minX) * scale) + 40;
                int y2 = (int)((p2.y - minY) * scale) + 40;
                g2.drawLine(x1,y1,x2,y2);
            }
        }
        // highlight path
        if (highlightPath != null && highlightPath.size() >= 2) {
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(4f));
            for (int i=0;i<highlightPath.size()-1;i++) {
                Point p1 = g.getCoord(highlightPath.get(i));
                Point p2 = g.getCoord(highlightPath.get(i+1));
                int x1 = (int)((p1.x - minX) * scale) + 40;
                int y1 = (int)((p1.y - minY) * scale) + 40;
                int x2 = (int)((p2.x - minX) * scale) + 40;
                int y2 = (int)((p2.y - minY) * scale) + 40;
                g2.drawLine(x1,y1,x2,y2);
            }
        }
        // draw nodes
        g2.setColor(Color.BLUE);
        for (Node n : g.getNodes()) {
            Point p = g.getCoord(n.getId());
            int x = (int)((p.x - minX) * scale) + 40;
            int y = (int)((p.y - minY) * scale) + 40;
            g2.fillOval(x-6,y-6,12,12);
            g2.setColor(Color.BLACK);
            g2.drawString(n.getId() + " " + n.getName(), x+8, y-8);
            g2.setColor(Color.BLUE);
        }
    }
}
