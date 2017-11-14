package main;

import filters.ClusterFilter;
import filters.NumberOfTweetsFilter;
import filters.MergeNamedEntityFilter;
import io.CSVProcessor;
import io.IOProcessor;
import model.Cluster;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MergeNamedEntities {

    private static final int NUMBER_OF_TWEETS = 9;
    private static final String FILE_EXTENSION = ".csv";
    private static final long window = 3600000;

    public static void main(String[] args) {
        String inputPath = args[0];
        String outputPath = args[1];

        // 7 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, 4 hours
        List<Long> windows = Arrays.asList(450000L, 900000L, 1800000L, 3600000L, 7200000L, 14400000L);

        for(long window: windows){
            runAnalysis(window, inputPath, outputPath);
        }
    }

    private static void runAnalysis(long window, String inputPath, String outputPath){
        long start = System.currentTimeMillis();

        IOProcessor processor = new CSVProcessor();
        ClusterFilter mergeNamedEntityFilter = new MergeNamedEntityFilter(window);
        ClusterFilter tweetFilter = new NumberOfTweetsFilter(NUMBER_OF_TWEETS);

        Map<String, Cluster> clusters = processor.readClusters(inputPath);
//        System.out.println(clusters.size());

        Collection<Cluster> byTweets = tweetFilter.execute(clusters.values());
        Collection<Cluster> byNamedEntity = mergeNamedEntityFilter.execute(byTweets);

//        System.out.println(byNamedEntity.size());

        long mins = (window / 1000) / 60;

        processor.writeClusters(byNamedEntity, outputPath + mins + FILE_EXTENSION);

        long stop = System.currentTimeMillis();

        System.out.println(stop - start);
    }
}
