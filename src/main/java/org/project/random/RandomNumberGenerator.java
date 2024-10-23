package org.project.random;

import java.util.Random;

public class RandomNumberGenerator implements IRandomNumberGenerator {
    final Random random = new Random();

    @Override
    public int getNumber(int bound) {
        return random.nextInt(bound);
    }
}
