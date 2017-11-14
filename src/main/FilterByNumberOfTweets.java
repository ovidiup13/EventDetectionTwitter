package main;

import filters.ClusterFilter;
import filters.NumberOfTweetsFilter;
import io.CSVProcessor;
import io.IOProcessor;
import model.Cluster;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class FilterByNumberOfTweets {

    private static final String FILE_EXTENSION = ".csv";

    public static void main(String[] args) throws IOException {
        String inputPath = args[0];
        String outputPath = args[1];

        int minNumberOfTweets = 5;
        int maxNumberOfTweets = 25;

        for(int i = minNumberOfTweets; i <= maxNumberOfTweets; i++){
            runAnalysis(i, inputPath, outputPath);
        }
    }

    private static void runAnalysis(int numberOfTweets, String inputPath, String outputPath){
        long start = System.currentTimeMillis();

        IOProcessor processor = new CSVProcessor();
        ClusterFilter filterByTweets = new NumberOfTweetsFilter(numberOfTweets);

        // read from csv file
        Map<String, Cluster> clusters = processor.readClusters(inputPath);

        // pipe into filter
        Collection<Cluster> filtered = filterByTweets.execute(clusters.values());

        // write to csv file
        processor.writeClusters(filtered, outputPath + numberOfTweets + FILE_EXTENSION);

        long stop = System.currentTimeMillis();

        // display performance
        System.out.println(stop - start);
    }
}
