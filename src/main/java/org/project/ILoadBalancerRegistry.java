package org.project;

import java.util.List;

public interface ILoadBalancerRegistry {
    void register(Instance instance);

    List<String> getInstances();

    String get();
}
