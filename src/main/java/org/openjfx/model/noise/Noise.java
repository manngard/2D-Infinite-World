package org.openjfx.model.noise;

import java.util.Random;

public class Noise {

    private OpenSimplexNoise noiseGenerator;
    private static Noise instance;

    private Noise() {
        this(new Random().nextLong());
    }

    private Noise(long seed) {
        noiseGenerator = new OpenSimplexNoise(seed);
    }

    public static Noise getInstance() {
        if(instance == null) {
            instance = new Noise();
        }

        return instance;
    }

    public void setSeed(long seed) {
        instance = new Noise(seed);
    }

    public double getValue(int xCoord, int yCoord) {
        return (noiseGenerator.eval(xCoord, yCoord) + 1) / 2;
    }

}
