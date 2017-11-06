package main;

import filters.CentroidCalculatorFilter;
import filters.ClusterFilter;
import filters.FilterByTweets;
import filters.MergeNamedEntityFilter;
import io.CSVProcessor;
import io.IOProcessor;
import model.Cluster;
import sun.plugin2.os.windows.FLASHWINFO;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CentroidTime {

    private static final int NUMBER_OF_TWEETS = 9;
    private static final String OUTPUT_FILE_NAME = "bycentroid";
    private static final String FILE_EXTENSION = ".csv";

    public static void main(String[] args) {
        String inputPath = args[0];
        String outputPath = args[1];

        long start = System.currentTimeMillis();

        IOProcessor processor = new CSVProcessor();
        ClusterFilter tweetFilter = new FilterByTweets(NUMBER_OF_TWEETS);
        ClusterFilter centroidFilter = new CentroidCalculatorFilter();
        ClusterFilter mergeNamedEntityFilter = new MergeNamedEntityFilter();

        Map<String, Cluster> clusters = processor.readClusters(inputPath);

        Collection<Cluster> byNumber = tweetFilter.execute(clusters.values());
        Collection<Cluster> byNamedEntity = mergeNamedEntityFilter.execute(byNumber);
//        Collection<Cluster> byCentroid = centroidFilter.execute(byNamedEntity);

//        Collection<Cluster> sorted = byCentroid.stream().sorted(Comparator.comparingLong(Cluster::getTimestamp)).collect(Collectors.toList());

        processor.writeClusters(byNamedEntity, outputPath + OUTPUT_FILE_NAME + FILE_EXTENSION);

        long stop = System.currentTimeMillis();

        System.out.println(stop - start);
    }
}
