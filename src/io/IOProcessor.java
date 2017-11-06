package io;

import model.Cluster;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IOProcessor {

    Map<String, Cluster> readClusters(String path);

    void writeClusters(Collection<Cluster> clusters, String path);
}
