package filters;

import model.Cluster;
import model.Tweet;

import java.util.*;

public class MergeNamedEntityFilter implements ClusterFilter {

//    private static final int WINDOW = 3600000; // 1 hour window

    @Override
    public Collection<Cluster> execute(Collection<Cluster> clusters) {

        Map<String, Cluster> clusterMap = new TreeMap<>();

        for(Cluster cluster : clusters){
            String namedEntity = cluster.getClusterNameEntity();
            if(clusterMap.containsKey(namedEntity)){
                Cluster c = clusterMap.get(namedEntity);
                c.addAllTweets(cluster.getTweets());
                clusterMap.replace(namedEntity, c);
            } else {
                clusterMap.put(namedEntity, cluster);
            }
        }

        return clusterMap.values();
    }

}
