package filters;

import model.Cluster;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

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
