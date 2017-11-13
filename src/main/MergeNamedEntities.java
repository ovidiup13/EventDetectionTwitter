package main;

import filters.ClusterFilter;
import filters.NumberOfTweetsFilter;
import filters.MergeNamedEntityFilter;
import io.CSVProcessor;
import io.IOProcessor;
import model.Cluster;

import java.util.Collection;
import java.util.Map;

public class MergeNamedEntities {

    private static final int NUMBER_OF_TWEETS = 9;
    private static final String OUTPUT_FILE_NAME = "bycentroid";
    private static final String FILE_EXTENSION = ".csv";

    public static void main(String[] args) {
        String inputPath = args[0];
        String outputPath = args[1];

        long start = System.currentTimeMillis();

        IOProcessor processor = new CSVProcessor();
        ClusterFilter tweetFilter = new NumberOfTweetsFilter(NUMBER_OF_TWEETS);
        ClusterFilter mergeNamedEntityFilter = new MergeNamedEntityFilter();

        Map<String, Cluster> clusters = processor.readClusters(inputPath);

        Collection<Cluster> byNumber = tweetFilter.execute(clusters.values());
        Collection<Cluster> byNamedEntity = mergeNamedEntityFilter.execute(byNumber);

        processor.writeClusters(byNamedEntity, outputPath + OUTPUT_FILE_NAME + FILE_EXTENSION);

        long stop = System.currentTimeMillis();

        System.out.println(stop - start);
    }
}
