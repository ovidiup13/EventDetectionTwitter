package main;

import io.CSVProcessor;
import io.IOProcessor;
import model.Cluster;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        String inputPath = args[0];
        String outputPath = args[1];

        IOProcessor processor = new CSVProcessor();

        Map<String, Cluster> clusters = processor.readClusters(inputPath);

        List<Cluster> filtered = clusters.values().stream().filter(cluster -> cluster.getTweets().size() > 10).collect(Collectors.toList());

        processor.writeClusters(filtered, outputPath);
    }
}
