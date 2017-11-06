package filters;

import model.Cluster;
import model.Tweet;

import java.util.Collection;

public class CentroidCalculatorFilter implements ClusterFilter {

    @Override
    public Collection<Cluster> execute(Collection<Cluster> clusters) {

        clusters.forEach(cluster -> {
            long centroid = cluster.getTweets().stream().mapToLong(Tweet::getTimestamp).sum() / cluster.getTweets().size();
            cluster.setTimestamp(centroid);
        });

        return clusters;
    }
}
