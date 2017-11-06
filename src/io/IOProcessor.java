package io;

import model.Cluster;

import java.util.Map;

public interface IOProcessor {

    Map<String, Cluster> readClusters(String path);

    void writeClusters(Map<String, Cluster> clusters, String path);
}
