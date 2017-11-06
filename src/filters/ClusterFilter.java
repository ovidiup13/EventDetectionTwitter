package filters;

import model.Cluster;

import java.util.Collection;
import java.util.List;

public interface ClusterFilter {

    List<Cluster> execute(Collection<Cluster> clusters);

}
