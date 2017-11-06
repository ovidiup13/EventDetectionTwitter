package io;

import model.Cluster;
import model.Tweet;

import java.io.*;
import java.util.*;

public class CSVProcessor implements IOProcessor {

    private static String COMMA = ",";
    private static String SPACE = " ";

    @Override
    public Map<String, Cluster> readClusters(String path) {
        Map<String, Cluster> clusters = new TreeMap<>();

        try {

            File fileInput = new File(path);
            InputStream inputFS = new FileInputStream(fileInput);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

            br.lines().forEach(line -> {
                String[] tokens = line.split(COMMA);

                String clusterId = tokens[0];
                String clusterNamedEntity = tokens[1];

                Cluster cluster = clusters.get(clusterId);
                if (cluster == null) {
                    cluster = new Cluster(clusterId);
                    cluster.setClusterNameEntity(clusterNamedEntity);
                    clusters.put(clusterId, cluster);
                }

                Tweet tweet = new Tweet();
                tweet.setTweetId(tokens[2]);
                tweet.setTimestamp(Long.parseLong(tokens[3]));
                tweet.setUserId(tokens[4]);
                tweet.setTweetTokens(Arrays.asList(tokens[5].split(SPACE)));
                tweet.setTweetText(tokens[6]);

                cluster.addTweet(tweet);

                clusters.replace(clusterId, cluster);
            });

            br.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return clusters;
    }

    @Override
    public void writeClusters(List<Cluster> clusters, String path) {

        try {

            File outputFile = new File(path);
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFile));
            BufferedWriter bw = new BufferedWriter(writer);

            clusters.forEach(cluster -> {
                List<String> tweets = clusterToLines(cluster);
                tweets.forEach(tweet -> {
                    try {
                        bw.write(tweet);
                        bw.newLine();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });
            });

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method that formats tweets line by line to be written in CSV file.
     * @param cluster {@link Cluster} model object
     * @return list of tweets
     */
    private List<String> clusterToLines(Cluster cluster){

        List<String> tweetLines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(Tweet tweet : cluster.getTweets()){

            if(cluster.getClusterId().equals("94")){
                System.out.println("here");
            }

            sb.append(cluster.getClusterId()).append(COMMA);
            sb.append(cluster.getClusterNameEntity()).append(COMMA);
            sb.append(tweet.getTweetId()).append(COMMA);
            sb.append(tweet.getTimestamp()).append(COMMA);
            sb.append(tweet.getUserId()).append(COMMA);
            sb.append(String.join(SPACE, tweet.getTweetTokens())).append(COMMA);
            sb.append(tweet.getTweetText());

            tweetLines.add(sb.toString());
            sb.setLength(0);
        }

        return tweetLines;
    }
}
