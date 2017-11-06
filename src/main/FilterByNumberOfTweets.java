package main;

import io.CSVProcessor;
import io.IOProcessor;
import model.Cluster;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterByNumberOfTweets {

    private static final int NUMBER_OF_TWEETS = 4;
    private static final String OUTPUT_FILE_NAME = "bynumber";
    private static final String FILE_EXTENSION = ".csv";

    public static void main(String[] args) throws IOException {
        String inputPath = args[0];
        String outputPath = args[1];

        long start = System.currentTimeMillis();

        IOProcessor processor = new CSVProcessor();

        Map<String, Cluster> clusters = processor.readClusters(inputPath);

        List<Cluster> filtered = clusters.values().stream().filter(cluster -> cluster.getTweets().size() > NUMBER_OF_TWEETS).collect(Collectors.toList());

        processor.writeClusters(filtered, outputPath + OUTPUT_FILE_NAME + NUMBER_OF_TWEETS + FILE_EXTENSION);

        long stop = System.currentTimeMillis();

        System.out.println(stop - start);
    }
}
