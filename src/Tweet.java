import java.util.List;

public class Tweet {

    private String tweetId;
    private long timestamp;
    private String userId;
    private List<String> tweetTokens;
    private String tweetText;

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getTweetTokens() {
        return tweetTokens;
    }

    public void setTweetTokens(List<String> tweetTokens) {
        this.tweetTokens = tweetTokens;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }
}
