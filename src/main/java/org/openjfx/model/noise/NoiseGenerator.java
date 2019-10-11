package org.openjfx.model.noise;

public interface NoiseGenerator {
    double getValue(double xCoord, double yCoord); // Returns value between and including 0 and 1
}
