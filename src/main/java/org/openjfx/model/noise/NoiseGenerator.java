package org.openjfx.model.noise;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public interface NoiseGenerator {
    double getValue(double xCoord, double yCoord); // Returns value between and including 0 and 1
}
