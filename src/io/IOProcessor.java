package io;

import model.Cluster;

import java.util.List;
import java.util.Map;

public interface IOProcessor {

    Map<String, Cluster> readClusters(String path);

    void writeClusters(List<Cluster> clusters, String path);
}
