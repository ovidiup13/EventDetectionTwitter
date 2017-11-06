package filters;

import model.Cluster;

import java.util.Collection;

public interface ClusterFilter {

    Collection<Cluster> execute(Collection<Cluster> clusters);

}
