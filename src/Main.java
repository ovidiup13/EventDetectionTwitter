import java.io.*;
import java.util.*;

public class Main {

    private static String COMMA = ",";
    private static String SPACE = " ";

    public static void main(String[] args) throws IOException {
        String path = args[0];
        Map<String, Cluster> tweets = processInputFile(path);
        System.out.println(tweets.size());
    }

    private static Map<String, Cluster> processInputFile(String path) {
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
                tweet.setTweetTokens(Arrays.asList(tokens[4].split(SPACE)));
                tweet.setTweetText(tokens[5]);

                cluster.addTweet(tweet);

                clusters.replace(clusterId, cluster);
            });

            br.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return clusters;
    }
}
