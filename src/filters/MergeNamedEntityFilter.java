package filters;

import model.Cluster;

import java.util.*;
import java.util.stream.Collectors;

public class MergeNamedEntityFilter implements ClusterFilter {

    private static final long DEFAULT_WINDOW = 3600000; // 1 hour window
    private long window;

    public MergeNamedEntityFilter() {
        this.window = DEFAULT_WINDOW;
    }

    public MergeNamedEntityFilter(long window) {
        this.window = window;
    }

    @Override
    public Collection<Cluster> execute(Collection<Cluster> clusters) {

        Map<String, List<Cluster>> clusterMap = new HashMap<>();

        for (Cluster cluster : clusters) {

            String namedEntity = cluster.getClusterNameEntity();

            if (!clusterMap.containsKey(namedEntity)) {
                List<Cluster> entityList = new ArrayList<>();
                entityList.add(cluster);
                clusterMap.put(namedEntity, entityList);
                continue;
            }

            List<Cluster> entityClusters = clusterMap.get(namedEntity);

            //go through previous clusters
            for (int i = entityClusters.size() - 1; i >= 0; i--) {
                Cluster c = entityClusters.get(i);

                //check timestamp difference
                if (cluster.getTimestamp() - c.getTimestamp() > window) {
                    entityClusters.add(cluster);
                    break;
                }

                if (cluster.getClusterNameEntity().equals(c.getClusterNameEntity())) {
                    // merge the two clusters
                    c.addAllTweets(cluster.getTweets());
                    break;
                }
            }

            entityClusters.sort(Comparator.comparingLong(Cluster::getTimestamp));
            clusterMap.replace(namedEntity, entityClusters);
        }

        return clusterMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }
}
