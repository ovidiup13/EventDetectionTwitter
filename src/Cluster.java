import java.util.ArrayList;
import java.util.List;

public class Cluster {

    private String clusterId;
    private String clusterNameEntity;
    private List<Tweet> tweets;
    private long timestamp;

    public Cluster() {
        tweets = new ArrayList<>();
    }

    public Cluster(String id) {
        this();
        this.clusterId = id;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getClusterNameEntity() {
        return clusterNameEntity;
    }

    public void setClusterNameEntity(String clusterNameEntity) {
        this.clusterNameEntity = clusterNameEntity;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void addTweet(Tweet tweet){
        this.tweets.add(tweet);
    }
}
