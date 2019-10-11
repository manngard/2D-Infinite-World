package org.openjfx.model.noise;

import java.util.Random;

public class DefaultNoiseGenerator implements NoiseGenerator {
    public double getValue(double xCoord, double yCoord) {
        return new Random().nextDouble() % 1.0;
    }
}
