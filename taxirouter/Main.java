package com.group9.taxirouter;

import java.io.IOException;
import javax.swing.SwingUtilities;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        System.out.println("Taxi Route Optimizer - CLI mode");
        try {
            Path basePath = FileSystems.getDefault().getPath("").toAbsolutePath();
            String nodesFile = basePath + "\\sample_data\\nodes.csv";
            String edgesFile = basePath + "\\sample_data\\edges.csv";
            Graph g = CSVLoader.loadGraph(nodesFile, edgesFile);
            // Start CLI in a separate thread so GUI remains responsive
            Thread cliThread = new Thread(() -> CLI.run(g));
            cliThread.setDaemon(true);
            cliThread.start();
            // Also launch GUI
            SwingUtilities.invokeLater(() -> {
                AppFrame frame = new AppFrame(g);
                frame.setVisible(true);
            });
        } catch (IOException e) {
            System.err.println("Error loading sample data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
