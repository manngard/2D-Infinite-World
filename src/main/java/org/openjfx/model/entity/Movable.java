package org.openjfx.model.entity;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

public interface Movable {
    enum Direction{
        LEFT, RIGHT, UP, DOWN
    }
    void move(Direction direction);
}
