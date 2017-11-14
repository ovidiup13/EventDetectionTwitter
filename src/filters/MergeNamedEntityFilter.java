package filters;

import model.Cluster;

import java.util.*;

public class MergeNamedEntityFilter implements ClusterFilter {

    private static final long DEFAULT_WINDOW = 3600000; // 1 hour window
    private long window;

    public MergeNamedEntityFilter(){
        this.window = DEFAULT_WINDOW;
    }

    public MergeNamedEntityFilter(long window){
        this.window = window;
    }

    @Override
    public Collection<Cluster> execute(Collection<Cluster> clusters) {

        Map<String, Cluster> clusterMap = new TreeMap<>();

        List<Cluster> previousClusters = new ArrayList<>();

        for(Cluster cluster: clusters){

            // add first element if empty
            if(previousClusters.isEmpty()){
                previousClusters.add(cluster);
                continue;
            }

            //go through previous clusters
            for(int i = previousClusters.size() - 1; i >= 0; i--){
                Cluster c = previousClusters.get(i);

                //check timestamp difference
                if(cluster.getTimestamp() - c.getTimestamp() > window){
                    previousClusters.add(cluster);
                    break;
                }

                if(cluster.getClusterNameEntity().equals(c.getClusterNameEntity())){
                    // merge the two clusters
                    c.addAllTweets(cluster.getTweets());
                    break;
                }
            }

            previousClusters.sort(Comparator.comparingLong(Cluster::getTimestamp));
        }

        return previousClusters;
    }
}
