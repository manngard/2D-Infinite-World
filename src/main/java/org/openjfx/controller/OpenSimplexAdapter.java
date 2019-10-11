package org.openjfx.controller;

import org.openjfx.model.noise.NoiseGenerator;
import org.openjfx.staticlibrary.OpenSimplexNoise;

import java.util.Random;

public class OpenSimplexAdapter implements NoiseGenerator {

    private OpenSimplexNoise noiseGenerator;
    private static OpenSimplexAdapter instance;

    private OpenSimplexAdapter() {
        this(new Random().nextLong());
    }

    private OpenSimplexAdapter(long seed) {
        noiseGenerator = new OpenSimplexNoise(seed);
    }

    public static OpenSimplexAdapter getInstance() {
        if(instance == null) {
            instance = new OpenSimplexAdapter();
        }

        return instance;
    }

    public void setSeed(long seed) {
        instance = new OpenSimplexAdapter(seed);
    }

    public double getValue(double xCoord, double yCoord) {
        return (noiseGenerator.eval(xCoord, yCoord) + 1) / 2;
    }

}
