package org.openjfx.model.noise;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility: Fallback NoiseGenerator in case none is passed in to the model
  Used by: Model
  Uses: NoiseGenerator
  */

public class DefaultNoiseGenerator implements NoiseGenerator {
    public double getValue(double xCoord, double yCoord) {
        return 0;
    }
}
