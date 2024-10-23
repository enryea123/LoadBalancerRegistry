import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.project.Instance;
import org.project.LoadBalancerRegistry;
import random.MockRandomNumberGenerator;

import java.util.List;

public class LoadBalancerRegistryTest {
    @Test
    void registerInstanceSuccess() {
        final Instance instance = new Instance("192.168.1.1");
        LoadBalancerRegistry registry = new LoadBalancerRegistry(new MockRandomNumberGenerator());
        registry.register(instance);
        Assertions.assertEquals(List.of(instance.getAddress()), registry.getInstances());
    }

    @Test
    void failRegisterOnDuplicateInstance() {
        final Instance instance = new Instance("192.168.1.1");
        LoadBalancerRegistry registry = new LoadBalancerRegistry(new MockRandomNumberGenerator());
        registry.register(instance);
        Assertions.assertThrows(IllegalStateException.class, () -> registry.register(instance));
    }

    @Test
    void failRegisterOnMaxCapacity() {
        final Instance instance1 = new Instance("192.168.1.1");
        final Instance instance2 = new Instance("192.168.1.2");
        LoadBalancerRegistry registry = new LoadBalancerRegistry(1, new MockRandomNumberGenerator());
        registry.register(instance1);
        Assertions.assertThrows(RuntimeException.class, () -> registry.register(instance2));
    }

    @Test
    void getRandomInstance() {
        final Instance instance1 = new Instance("192.168.1.1");
        final Instance instance2 = new Instance("192.168.1.2");
        final MockRandomNumberGenerator randomNumberGenerator = new MockRandomNumberGenerator();
        randomNumberGenerator.setReturnValue(1);
        LoadBalancerRegistry registry = new LoadBalancerRegistry(randomNumberGenerator);
        registry.register(instance1);
        registry.register(instance2);
        Assertions.assertEquals(instance2.getAddress(), registry.get());
        Assertions.assertEquals(instance2.getAddress(), registry.get());
        randomNumberGenerator.setReturnValue(0);
        Assertions.assertEquals(instance1.getAddress(), registry.get());
    }
}
