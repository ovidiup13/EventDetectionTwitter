package filters;

import model.Cluster;

import java.util.Collection;
import java.util.stream.Collectors;

public class NumberOfTweetsFilter implements ClusterFilter {

    private int numberOfTweets;

    public NumberOfTweetsFilter(int n) {
        this.numberOfTweets = n;
    }

    @Override
    public Collection<Cluster> execute(Collection<Cluster> clusters) {
        return clusters.stream().filter(cluster -> cluster.getTweets().size() > numberOfTweets).collect(Collectors.toList());
    }
}
