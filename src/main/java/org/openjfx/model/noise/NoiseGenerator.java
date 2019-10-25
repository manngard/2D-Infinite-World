package org.openjfx.model.noise;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility: Allows for usability of diffrent noise generators
  Used by: TileFactory, Model, OpenSimplexAdapter
  */

public interface NoiseGenerator {
    double getValue(double xCoord, double yCoord); // Returns value between and including 0 and 1
}
