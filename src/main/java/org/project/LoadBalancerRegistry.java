package org.project;

import org.project.random.IRandomNumberGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoadBalancerRegistry implements ILoadBalancerRegistry {
    private static final int DEFAULT_SIZE = 10;
    private final int size;
    private final IRandomNumberGenerator randomNumberGenerator;
    private final Set<String> addresses = new HashSet<>();

    public LoadBalancerRegistry(IRandomNumberGenerator randomNumberGenerator) {
        this.size = DEFAULT_SIZE;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public LoadBalancerRegistry(int size, IRandomNumberGenerator randomNumberGenerator) {
        this.size = size;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public void register(Instance instance) {
        if (addresses.size() >= size) {
            throw new RuntimeException("Maximum number of instances already registered");
        }

        if (addresses.contains(instance.getAddress())) {
            throw new IllegalStateException("Instance address already registered");
        }

        addresses.add(instance.getAddress());
    }

    @Override
    public List<String> getInstances() {
        return addresses.stream().toList();
    }

    @Override
    public String get() {
        final int randomIndex = randomNumberGenerator.getNumber(addresses.size());
        int i = 0;
        for (String address : addresses) {
            if (i == randomIndex) {
                return address;
            }
            i++;
        }
        throw new IllegalStateException("Something went wrong when picking random address");
    }
}
